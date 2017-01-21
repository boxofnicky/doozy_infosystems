package com.doozyinfosystem.sample.doozyinfosystem;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;

public class CartDetailActivity extends AppCompatActivity {
    private static final int CHECK_OUT_MENU = 555;
    public Button button;
    Product product;
    int buttonState = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.detailRelative);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.addRule(RelativeLayout.RIGHT_OF, R.id.detailLinear);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);

        button = new Button(this);
        button.setText("Remove from cart");
        button.setLayoutParams(params);
        relativeLayout.addView(button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

cartFlip();
            }
        });


        String id = getIntent().getStringExtra(CartActivity.ITEM_ID);
        product = DataProvider.getProductMap().get(id);

        TextView nameView = (TextView) findViewById(R.id.nameText);
        nameView.setText(product.getName());

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,CHECK_OUT_MENU,222,"Check Out").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
         return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==CHECK_OUT_MENU)
        {
           cartFlip();
        }
        return super.onOptionsItemSelected(item);
    }


    public void cartFlip()
    {
        if(buttonState==0) {
            DataProvider.getCartItems().remove(product);
            Intent intent = new Intent();
            intent.putExtra(CartActivity.DETAIL_REQUEST_DATA, product.getName());
            Toast.makeText(CartDetailActivity.this, "Item successfully removed from Cart.", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK, intent);
            button.setText("Add to Cart");
            buttonState = 1;
        }
        else
        {
            DataProvider.getCartItems().add(product);
            Intent intent = new Intent();
            intent.putExtra(CartActivity.DETAIL_REQUEST_DATA,product.getName());
            Toast.makeText(CartDetailActivity.this, "Item successfully added to Cart.", Toast.LENGTH_SHORT).show();
            setResult(RESULT_CANCELED,intent);
            button.setText("Remove from Cart");
            buttonState=0;
        }
    }

}