package com.doozyinfosystem.sample.doozyinfosystem;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by Doozy on 06-01-2017.
 */


public class ProductArrayAdapterList extends ArrayAdapter<Product> {

    private List<Product> products;

    public ProductArrayAdapterList(Context context, int resource, List<Product> objects) {
        super(context, resource, objects);
        products = objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view, parent, false);
        }

        Product product = products.get(position);

        for (int i = 0; i < 3; i++) {
            TextView tv;
            switch (i) {
                case 0:
                    tv = (TextView) convertView.findViewById(R.id.nameText);
                    tv.setText(product.getName());
                    break;
                case 1:
                    NumberFormat formatter = NumberFormat.getCurrencyInstance();
                    tv = (TextView) convertView.findViewById(R.id.priceText);
                    tv.setText(formatter.format(product.getPrice()));
                    break;
            }
        }
        //to find the image from the assets

//        ImageView imageView = (ImageView) convertView.findViewById(R.id.itemImage);
//        imageView.setImageBitmap(getBitmap(product.getId() + ".png"));


        try {
            InputStream inputStream = getContext().getAssets().open(product.getId() + ".png");
            ImageView imageView = (ImageView) convertView.findViewById(R.id.itemImage);
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            imageView.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convertView;
    }

//// this code can also be used to retrieve image
//    private Bitmap getBitmap(String productId) {
//        AssetManager assetManager = getContext().getAssets();
//        try {
//            InputStream inputStream = assetManager.open(productId);
//            return BitmapFactory.decodeStream(inputStream);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
