package com.doozyinfosystem.sample.doozyinfosystem;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Doozy on 17-01-2017.
 */

public class ProductDataAdapter extends RecyclerView.Adapter<ProductDataAdapter.DataViewHolder> {
    public static final String ITEM_KEY = "Item_key";
    private List<Product> nameList = new ArrayList<>();
    private Context context;

    public ProductDataAdapter(Context context, List<Product> dataNameList) {
        this.context = context;
        this.nameList = dataNameList;

    }


    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_view, parent, false);
        DataViewHolder viewHolder = new DataViewHolder(view);
        return viewHolder;

    }


    @Override
    public void onBindViewHolder(DataViewHolder holder, final int position) {
        holder.tvName.setText(nameList.get(position).getName());
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        holder.tvDesc.setText(numberFormat.format(nameList.get(position).getPrice()));
        InputStream inputStream = null;

        try {
            inputStream = context.getAssets().open(nameList.get(position).getId() + ".png");
            Drawable d = Drawable.createFromStream(inputStream, null);
            holder.imageView.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(MainActivity.PRODUCT_ID, nameList.get(position));
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }


    public class DataViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public TextView tvDesc;
        public View mView;
        public ImageView imageView;

        public DataViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            tvName = (TextView) itemView.findViewById(R.id.nameText);
            tvDesc = (TextView) itemView.findViewById(R.id.priceText);
            imageView = (ImageView) itemView.findViewById(R.id.itemImage);
        }
    }
}
