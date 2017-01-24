package com.doozyinfosystem.sample.doozyinfosystem;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import com.doozyinfosystem.sample.doozyinfosystem.db.Tables;

/**
 * Created by Doozy on 06-01-2017.
 */
public class Product implements Parcelable {
    private String id;
    private String name;
    private String desc;
    private double price;
    private String category;

    public Product(String id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.desc = description;
        this.price = price;
        this.category = "Miscellaneous";
    }

    public Product(String id, String name, String desc, double price, String category) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.category = category;
    }

    public String getId() {

        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDesc() {
        return this.desc;
    }


    public double getPrice() {
        return this.price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.desc);
        dest.writeDouble(this.price);
    }

    protected Product(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.desc = in.readString();
        this.price = in.readDouble();
    }
public ContentValues getValues(){
    ContentValues value=new ContentValues(5);
    value.put(Tables.COLUMN_ITEM_ID,id);
    value.put(Tables.COLUMN_NAME,name);
    value.put(Tables.COLUMN_DESCRIPTION,desc);
    value.put(Tables.COLUMN_PRICE,price);
    value.put(Tables.COLUMN_CATEGORY,category);
    return value;

}
    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

}
