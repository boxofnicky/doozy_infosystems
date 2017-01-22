package com.doozyinfosystem.sample.doozyinfosystem;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Doozy on 22-01-2017.
 */

public class JSONHelper {
    public static final String FILE_NAME = "JSON_file.txt";
    public static Context mContext;
    public static String jsonString;

    public static boolean exportJson(Context context, List<Product> productList) {
        mContext = context;
        Gson gson = new Gson();
        DataItems items = new DataItems();
        items.productList = productList;
        jsonString = gson.toJson(items);
        FileOutputStream fileOutputStream = null;
        File file = new File(Environment.getExternalStorageDirectory(), FILE_NAME);

        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(jsonString.getBytes());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {

            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Product> importJson() {
        Gson gson = new Gson();
        FileReader reader=null;
        File file=new File(Environment.getExternalStorageDirectory(),FILE_NAME);


        try {
            reader=new FileReader(file);
            DataItems items=gson.fromJson(reader,DataItems.class);
            return items.getProductList();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(mContext, "File not found.", Toast.LENGTH_LONG).show();
            return new ArrayList<Product>();
        }

    }

    public static class DataItems {
        List<Product> productList;

        public List<Product> getProductList() {
            return productList;
        }

        public void setProductList(List<Product> productList) {
            this.productList = productList;
        }


    }


}
