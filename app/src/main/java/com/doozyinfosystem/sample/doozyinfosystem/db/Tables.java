package com.doozyinfosystem.sample.doozyinfosystem.db;

/**
 * Created by Doozy on 23-01-2017.
 */

public class Tables {
    public static final String TABLE_ITEMS = "items";
    public static final String COLUMN_ITEM_ID = "itemID";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_CATEGORY = "category";
    public static final String[] ALL_COLUMNS_ITEMS = {COLUMN_ITEM_ID, COLUMN_NAME, COLUMN_DESCRIPTION, COLUMN_PRICE, COLUMN_CATEGORY};

    public static final String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_ITEMS + "(" +
            COLUMN_ITEM_ID + " TEXT PRIMARY KEY," +
            COLUMN_NAME + " TEXT," +
            COLUMN_DESCRIPTION + " TEXT," +
            COLUMN_PRICE + " REAL," +
            COLUMN_CATEGORY + " TEXT);";

    public static final String DELETE_ITEM_TABLE = "DROP TABLE " + TABLE_ITEMS;

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "usersID";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASS = "password";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_PHONE = "phone";
    public static final String[] ALL_COLUMNS_USERS = {COLUMN_USER_ID, COLUMN_NAME, COLUMN_EMAIL, COLUMN_PASS, COLUMN_ADDRESS, COLUMN_PHONE};
    public static final String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USERS + "(" +
            COLUMN_USER_ID + " TEXT PRIMARY KEY," +
            COLUMN_NAME + " TEXT," +
            COLUMN_EMAIL + " TEXT," +
            COLUMN_PASS + " TEXT," +
            COLUMN_ADDRESS + " TEXT," +
            COLUMN_PHONE + " TEXT);";

    public static final String DELETE_USER_TABLE = "DROP TABLE " + TABLE_USERS;

    public static final String TABLE_CART = "cart";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String[] ALL_COLUMNS_CART = {COLUMN_USER_ID, COLUMN_ITEM_ID, COLUMN_QUANTITY};

    public static final String CREATE_CART_TABLE = "CREATE TABLE " + TABLE_CART + "(" +
            COLUMN_USER_ID + " TEXT," +
            COLUMN_ITEM_ID + " TEXT PRIMARY KEY," +
            COLUMN_QUANTITY + " TEXT);";
    public static final String DELETE_CART_TABLE = "DROP TABLE " + TABLE_CART;

}
