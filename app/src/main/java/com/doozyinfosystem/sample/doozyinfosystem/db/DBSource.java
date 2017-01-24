package com.doozyinfosystem.sample.doozyinfosystem.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.lang.UScript;

import com.doozyinfosystem.sample.doozyinfosystem.CartItem;
import com.doozyinfosystem.sample.doozyinfosystem.MainActivity;
import com.doozyinfosystem.sample.doozyinfosystem.Product;
import com.doozyinfosystem.sample.doozyinfosystem.User;

import java.util.ArrayList;
import java.util.List;

public class DBSource {
    private final Context mContext;
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase mDatabase;

    public DBSource(Context context) {
        this.mContext = context;
        openHelper = new DBHelper(mContext);
        mDatabase = openHelper.getWritableDatabase();
    }

    public void open() {
        mDatabase = openHelper.getWritableDatabase();
    }

    public void close() {
        mDatabase.close();
    }

    public Product addItem(Product product) {
        ContentValues values = product.getValues();
        mDatabase.insert(Tables.TABLE_ITEMS, null, values);
        return product;
    }

    public User addUser(User user) {
        ContentValues values = user.getValues();
        mDatabase.insert(Tables.TABLE_USERS, null, values);
        return user;


    }

    public CartItem addCartItem(CartItem cartItem) {
        ContentValues values;
        values = cartItem.getValue();
        mDatabase.insert(Tables.TABLE_CART, null, values);
        return cartItem;
    }

    public long countRows(String tableName) {
        return DatabaseUtils.queryNumEntries(mDatabase, tableName);
    }

    public List<Product> getAllItems() {
        Cursor cursor = mDatabase.query(Tables.TABLE_ITEMS, Tables.ALL_COLUMNS_ITEMS, null, null, null, null, Tables.COLUMN_NAME);
        List<Product> products = new ArrayList<>();
        while (cursor.moveToNext()) {
            Product item = new Product(
                    cursor.getString(cursor.getColumnIndex(Tables.COLUMN_ITEM_ID)),
                    cursor.getString(cursor.getColumnIndex(Tables.COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndex(Tables.COLUMN_DESCRIPTION)),
                    cursor.getDouble(cursor.getColumnIndex(Tables.COLUMN_PRICE)),
                    cursor.getString(cursor.getColumnIndex(Tables.COLUMN_CATEGORY)));
            products.add(item);
        }
        cursor.close();
        return products;
    }

    public boolean isCartItem(Product item) {
        Cursor cursor;
        try {
            cursor = mDatabase.query(Tables.TABLE_CART, Tables.ALL_COLUMNS_CART, null, null, null, null, null);

            if (countRows(Tables.TABLE_CART) > 0) {
                while (cursor.moveToNext()) {
                    if (item.getId().equals(cursor.getString(cursor.getColumnIndex(Tables.COLUMN_ITEM_ID))))
                        return true;
                }

            }
            cursor.close();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;

        }
        return false;
    }

    public List<User> getAllUsers() {
        Cursor cursor = mDatabase.query(Tables.TABLE_USERS, Tables.ALL_COLUMNS_USERS, null, null, null, null, Tables.COLUMN_NAME);
        List<User> users = new ArrayList<>();
        while (cursor.moveToNext()) {
            User user = new User(
                    cursor.getString(cursor.getColumnIndex(Tables.COLUMN_USER_ID)),
                    cursor.getString(cursor.getColumnIndex(Tables.COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndex(Tables.COLUMN_EMAIL)),
                    cursor.getString(cursor.getColumnIndex(Tables.COLUMN_PASS)),
                    cursor.getString(cursor.getColumnIndex(Tables.COLUMN_ADDRESS)),
                    cursor.getString(cursor.getColumnIndex(Tables.COLUMN_PHONE)));
            users.add(user);
        }
        cursor.close();
        return users;
    }

    public List<CartItem> getAllCarts() {
        Cursor cursor = mDatabase.query(Tables.TABLE_CART, Tables.ALL_COLUMNS_CART, null, null, null, null, null);
        List<CartItem> cartItems = new ArrayList<>();
        while (cursor.moveToNext()) {
            CartItem cartItem = new CartItem(cursor.getString(cursor.getColumnIndex(Tables.COLUMN_USER_ID)),
                    cursor.getString(cursor.getColumnIndex(Tables.COLUMN_ITEM_ID)),
                    cursor.getInt(cursor.getColumnIndex(Tables.COLUMN_QUANTITY)));
            cartItems.add(cartItem);
        }
        cursor.close();
        return cartItems;

    }

    public int removeFromCart(Product product) {
        int i=0;
        if(isCartItem(product)) {
            i = mDatabase.delete(Tables.TABLE_CART, Tables.COLUMN_ITEM_ID + "='" + product.getId()+"'", null);
        }
        return i;
    }

}
