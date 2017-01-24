package com.doozyinfosystem.sample.doozyinfosystem.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Doozy on 23-01-2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "doozy.db";
    public static final int dbVersion = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Tables.CREATE_ITEM_TABLE);
        db.execSQL(Tables.CREATE_USER_TABLE);
        db.execSQL(Tables.CREATE_CART_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Tables.DELETE_ITEM_TABLE);
        db.execSQL(Tables.DELETE_USER_TABLE);
        db.execSQL(Tables.DELETE_CART_TABLE);
        onCreate(db);
    }
}
