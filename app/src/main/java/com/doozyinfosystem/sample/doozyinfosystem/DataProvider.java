package com.doozyinfosystem.sample.doozyinfosystem;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Doozy on 06-01-2017.
 */
public class DataProvider {
    private static List<Product> products = new ArrayList<>();
    private static Map<String, Product> productMap = new HashMap<String, Product>();
    private static List<Product> cartItems = JSONHelper.importJson();
    private static List<Customer> customerList = new ArrayList<>();
    private static Map<String, Customer> customerMap = new HashMap<>();

    static {
        addItem("scanner101", "RS-734 Scanner", "Automatic scanner and other scanner description..text laldksjfh", 5000.00);
        addItem("cpu102", "Octa Core CPU", "Details features and other description about the item..text", 11000.00);
        addItem("hdd103", "HDD-4TB", "Details features and other description about the item..text", 4000.00);
        addItem("mouse104", "Mouse - RS64", "Details features and other description about the item..text", 400.00);
        addItem("cam105", "Camera DW-76", "Details features and other description about the item..text", 11000.00);
        addItem("mouse106", "Mouse 85-B", "Details features and other description about the item..text", 700.00);
        addItem("keyboard107", "Keyboard DS847", "Details features and other description about the item..text", 1100);
        addItem("scanner101", "RS-734 Scanner", "Automatic scanner and other scanner description..text laldksjfh", 5000.00);

        addCustomer(new Customer("box.of.nicky", "Nicky Ghanghas", "box.of.nicky@gmail.com", "GH4NGH4SN4V33N", "R-Z 54, Sai Baba Enclave(Part-B), Najafgarh, New Delhi-110043", "8130805381"));
        addCustomer(new Customer("neokit2008", "Naveen Kumar", "neokit2008@gmail.com", "GH4NGH4SN4V33N", "R-Z 54, Sai Baba Enclave(Part-B), Najafgarh, New Delhi-110043", "8130805381"));
        addCustomer(new Customer("doozyinfosystems", "Doozy Infosystems", "doozyinfosystems@gmail.com", "GH4NGH4SN4V33N", "R-Z 54, Sai Baba Enclave(Part-B), Najafgarh, New Delhi-110043", "8130805381"));
    }


    public static void addCustomer(Customer user) {
        customerList.add(user);
        customerMap.put(user.getCustomerID(), user);
    }

    public static List<Customer> getCustomerList() {

        return customerList;
    }

    public static Map<String, Customer> getCustomerMap() {

        return customerMap;
    }

    public static List<Product> getProducts() {

        return products;
    }

    public static Map<String, Product> getProductMap() {
        return productMap;
    }

    public static void addItem(String id, String name, String desc, double price) {
        Product product = new Product(id, name, desc, price);
        products.add(product);
        productMap.put(id, product);
    }

    public static List<Product> getCartItems() {
        return cartItems;
    }


    public static boolean removeFromCart(String item) {
        Product product = productMap.get(item);
        if (cartItems.contains(product)) {
            cartItems.remove(product);
            return true;
        } else
            return false;
    }


    public static void addToCart(Context context, Product item) {
        cartItems.add(item);
        JSONHelper.exportJson(context, cartItems);
    }
}
