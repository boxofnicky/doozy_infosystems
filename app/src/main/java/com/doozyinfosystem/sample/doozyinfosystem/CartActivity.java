package com.doozyinfosystem.sample.doozyinfosystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    public static final String ITEM_ID = "ITEM_ID";
    public static final String DETAIL_REQUEST_DATA="DETAIL_REQUEST_DATA";
    private static final int REQUEST_CODE = 1012;
    private List<Product> cartItems = DataProvider.getCartItems();
private ListView listView;
    private ProductArrayAdapterList adapter;
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
        adapter = new ProductArrayAdapterList(this, R.layout.list_view, cartItems);
        listView = (ListView) findViewById(R.id.cartListView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(CartActivity.this,CartDetailActivity.class);
                intent.putExtra(ITEM_ID,cartItems.get(position).getId());
                startActivityForResult(intent,REQUEST_CODE);

            }

        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        listView.setAdapter(adapter);

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
