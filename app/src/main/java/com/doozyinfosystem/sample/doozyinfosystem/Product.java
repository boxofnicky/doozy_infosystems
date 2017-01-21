package com.doozyinfosystem.sample.doozyinfosystem;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Doozy on 06-01-2017.
 */
public class Product implements Parcelable {
    private String id;
    private String name;
    private String desc;
    private double price;

    public Product(String id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.desc = description;
        this.price=price;
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
