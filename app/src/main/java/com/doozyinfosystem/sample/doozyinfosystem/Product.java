package com.doozyinfosystem.sample.doozyinfosystem;

/**
 * Created by Doozy on 06-01-2017.
 */
public class Product {
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

}
