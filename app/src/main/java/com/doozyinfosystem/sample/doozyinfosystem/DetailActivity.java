package com.doozyinfosystem.sample.doozyinfosystem;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;

public class DetailActivity extends AppCompatActivity {
    private static final int ADD_CART = 548;
    public CoordinatorLayout coordinatorLayout;
    private String productName;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.detailCoordinator);

        productName = getIntent().getStringExtra(MainActivity.PRODUCT_ID);
        product = ProductDataProvider.getProductMap().get(productName);
        TextView nameText = (TextView) findViewById(R.id.nameText);
        nameText.setText(product.getName());

        TextView descText = (TextView) findViewById(R.id.descriptionText);
        descText.setText(product.getDesc());

        TextView priceText = (TextView) findViewById(R.id.priceText);
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        priceText.setText(numberFormat.format(product.getPrice()));

        Bitmap bitmap = getBitmap(product.getId());
        ImageView imageView = (ImageView) findViewById(R.id.itemImage);
        imageView.setImageBitmap(bitmap);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart();

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    Bitmap getBitmap(String imageName) {

        try {
            InputStream is = getAssets().open(imageName + ".png");
            return BitmapFactory.decodeStream(is);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public void addToCart() {
        Intent intent = new Intent();
        intent.putExtra(MainActivity.RESULT_DATA, product.getName() + " added to your cart.");
        setResult(MainActivity.RESULT_OK, intent);
        ProductDataProvider.addToCart(product);
        Snackbar.make(coordinatorLayout, product.getName() + " added to your cart.", Snackbar.LENGTH_LONG).setAction("Show Cart", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailActivity.this, "This display cart window.", Toast.LENGTH_SHORT).show();
            }
        }).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, ADD_CART, 222, "Add to Cart").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == ADD_CART) {
            addToCart();
        }

        return super.onOptionsItemSelected(item);
    }
}
