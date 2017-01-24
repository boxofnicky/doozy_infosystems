package com.doozyinfosystem.sample.doozyinfosystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.doozyinfosystem.sample.doozyinfosystem.db.DBSource;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class CartDetailActivity extends AppCompatActivity {
    private static final int CHECK_OUT_MENU = 555;
    private  Product product;
    private int buttonState = 0;
    public Button button;
    private DBSource dbSource;
    private List<Product> cart = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        product = getIntent().getParcelableExtra(MainActivity.PRODUCT_ID);
        TextView nameView = (TextView) findViewById(R.id.nameText);
        nameView.setText(product.getName());

        displayButton();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartFlip();
            }
        });

        TextView priceView = (TextView) findViewById(R.id.priceText);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        priceView.setText(formatter.format(product.getPrice()));

        TextView descView = (TextView) findViewById(R.id.descriptionText);
        descView.setText(product.getDesc());

        try {
            InputStream inputStream = getAssets().open(product.getId() + ".png");
            ImageView imageView = (ImageView) findViewById(R.id.itemImage);
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            imageView.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void displayButton() {
        dbSource = new DBSource(this);
        dbSource.open();
        button = (Button) findViewById(R.id.button);
        if (dbSource.isCartItem(product)) {
            button.setText(R.string.remove_from_cart);
            buttonState = 0;
        } else {
            button.setText(R.string.add_to_cart);
            buttonState = 1;
        }
        dbSource.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        dbSource.open();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, CHECK_OUT_MENU, 222, "Check Out").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == CHECK_OUT_MENU) {
            cartFlip();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        dbSource.close();
    }

    public void cartFlip() {
        if (buttonState == 0) {
            if (DataProvider.removeFromCart(product, this)) {

                Intent intent = new Intent();
                intent.putExtra(CartActivity.DETAIL_REQUEST_DATA, product.getName());
                Toast.makeText(CartDetailActivity.this, "Item successfully removed from CartItem.", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK, intent);
                button.setText(R.string.add_to_cart);
                buttonState = 1;
            } else {
                Toast.makeText(CartDetailActivity.this, "Item not successfully removed from CartItem.", Toast.LENGTH_SHORT).show();
            }
        } else if (DataProvider.addToCart(this, product)) {
            Intent intent = new Intent();
            intent.putExtra(CartActivity.DETAIL_REQUEST_DATA, product.getName());
            Toast.makeText(CartDetailActivity.this, "Item successfully added to CartItem.", Toast.LENGTH_SHORT).show();
            setResult(RESULT_CANCELED, intent);
            button.setText(R.string.remove_from_cart);
            buttonState = 0;
        }
        displayButton();
    }

}