package com.doozyinfosystem.sample.doozyinfosystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.doozyinfosystem.sample.doozyinfosystem.db.DBHelper;
import com.doozyinfosystem.sample.doozyinfosystem.db.DBSource;
import com.doozyinfosystem.sample.doozyinfosystem.db.Tables;

import java.util.List;
import java.util.Map;

/**
 * Created by Doozy on 23-01-2017.
 */

public class CartItem {

    public int quantity;
    public String customerID;
    public String itemID;

    public CartItem(String customerID, String itemID, int quantity) {
        this.quantity = quantity;
        this.customerID = customerID;
        this.itemID = itemID;

    }
    public ContentValues getValue(){
        ContentValues values=new ContentValues(3);
        values.put(Tables.COLUMN_QUANTITY,quantity);
        values.put(Tables.COLUMN_USER_ID,customerID);
        values.put(Tables.COLUMN_ITEM_ID,itemID);
        return values;
    }

}



