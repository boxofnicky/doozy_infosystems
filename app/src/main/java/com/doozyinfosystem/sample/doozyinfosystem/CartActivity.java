package com.doozyinfosystem.sample.doozyinfosystem;

import android.content.Intent;
import android.content.SharedPreferences;
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

import java.text.NumberFormat;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    public static final String ITEM_ID = "ITEM_ID";
    private static final int REQUEST_CODE = 1012;
    public static final String DETAIL_REQUEST_DATA = "DETAIL_REQUEST_DATA";
    private List<Product> cartItems = DataProvider.getCartItems();
    private RecyclerView recyclerView;
    private ProductDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView tvCartItems = (TextView) findViewById(R.id.cartCount);
        TextView tvCartCost = (TextView) findViewById(R.id.cartPrice);
        if (cartItems.isEmpty()) {
            tvCartItems.setText("No Items in Cart");
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
        recyclerView.setAdapter(adapter);

        TextView tvCartItems = (TextView) findViewById(R.id.cartCount);
        TextView tvCartCost = (TextView) findViewById(R.id.cartPrice);
        if (cartItems.isEmpty()) {
            tvCartItems.setText("No Items in Cart");
        } else {

            tvCartItems.setText("Items: " + cartItems.size());
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            tvCartCost.setText("Total: " + formatter.format(getTotal()));
        }

    }

    private double getTotal() {
        double total = 0;
        for (Product product : cartItems) {
            total += product.getPrice();
        }
        return total;
    }

}
