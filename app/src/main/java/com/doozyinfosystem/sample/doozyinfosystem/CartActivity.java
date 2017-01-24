package com.doozyinfosystem.sample.doozyinfosystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.doozyinfosystem.sample.doozyinfosystem.db.DBSource;
import com.doozyinfosystem.sample.doozyinfosystem.db.Tables;

import java.text.NumberFormat;
import java.util.List;

import javax.sql.DataSource;

public class CartActivity extends AppCompatActivity {
    public static final String ITEM_ID = "ITEM_ID";
    private static final int REQUEST_CODE = 1012;
    public static final String DETAIL_REQUEST_DATA = "DETAIL_REQUEST_DATA";
    private List<Product> cartItems;
    private RecyclerView recyclerView;
    private ProductDataAdapter adapter;
    DBSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setLayout();
    }

    public void setLayout() {
        dataSource = new DBSource(this);
        dataSource.open();
        try {
            cartItems = DataProvider.getCartItems(this);
        } catch (SQLiteException e) {
            Toast.makeText(this, "Cart activity error.", Toast.LENGTH_LONG).show();
            e.printStackTrace();

        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView tvCartItems = (TextView) findViewById(R.id.cartCount);
        TextView tvCartCost = (TextView) findViewById(R.id.cartPrice);
        if (cartItems.isEmpty()) {
            tvCartItems.setText("No Items in CartItem");
        } else {

            tvCartItems.setText("Items: " + cartItems.size());
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            tvCartCost.setText("Total: " + formatter.format(getTotal()));
        }
        adapter = new ProductDataAdapter(this, cartItems);
        recyclerView = (RecyclerView) findViewById(R.id.cartRecyclerView);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean grid = preferences.getBoolean(getString(R.string.grid_view_pref), false);
        if (grid)
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(adapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        setLayout();
    }

    private double getTotal() {
        double total = 0;
        for (Product product : cartItems) {
            total += product.getPrice();
        }
        return total;
    }

}
