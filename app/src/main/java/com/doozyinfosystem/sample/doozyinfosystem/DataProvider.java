package com.doozyinfosystem.sample.doozyinfosystem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.doozyinfosystem.sample.doozyinfosystem.db.DBHelper;
import com.doozyinfosystem.sample.doozyinfosystem.db.DBSource;
import com.doozyinfosystem.sample.doozyinfosystem.db.Tables;

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
    private static List<Product> cartItems = new ArrayList<>();
    private static List<User> userList = new ArrayList<>();
    private static Map<String, User> customerMap = new HashMap<>();
    private static Map<String, CartItem> carts;
    private static DBSource dbSource;

    static {
        addItem("scanner101", "RS-734 Scanner", "Automatic scanner and other scanner description..text laldksjfh", 5000.00);
        addItem("cpu102", "Octa Core CPU", "Details features and other description about the item..text", 11000.00);
        addItem("hdd103", "HDD-4TB", "Details features and other description about the item..text", 4000.00);
        addItem("mouse104", "Mouse - RS64", "Details features and other description about the item..text", 400.00);
        addItem("cam105", "Camera DW-76", "Details features and other description about the item..text", 11000.00);
        addItem("mouse106", "Mouse 85-B", "Details features and other description about the item..text", 700.00);
        addItem("keyboard107", "Keyboard DS847", "Details features and other description about the item..text", 1100);
        addItem("scanner101", "RS-734 Scanner", "Automatic scanner and other scanner description..text laldksjfh", 5000.00);

        addUser(new User("box.of.nicky", "Nicky Ghanghas", "box.of.nicky@gmail.com", "GH4NGH4SN4V33N", "R-Z 54, Sai Baba Enclave(Part-B), Najafgarh, New Delhi-110043", "8130805381"));
        addUser(new User("neokit2008", "Naveen Kumar", "neokit2008@gmail.com", "GH4NGH4SN4V33N", "R-Z 54, Sai Baba Enclave(Part-B), Najafgarh, New Delhi-110043", "8130805381"));
        addUser(new User("doozyinfosystems", "Doozy Infosystems", "doozyinfosystems@gmail.com", "GH4NGH4SN4V33N", "R-Z 54, Sai Baba Enclave(Part-B), Najafgarh, New Delhi-110043", "8130805381"));
    }


    public static void addUser(User user) {
        userList.add(user);
        customerMap.put(user.getCustomerID(), user);
    }

    public static List<User> getUserList() {

        return userList;
    }

    public static Map<String, User> getCustomerMap() {

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

    public static List<Product> getCartItems(Context context) {
        dbSource = new DBSource(context);
        dbSource.open();
        List<CartItem> cartItemList = dbSource.getAllCarts();
        List<Product> cartContent = new ArrayList<>();
        for (Product p : dbSource.getAllItems()) {
            productMap.put(p.getId(), p);
        }
        for (CartItem c : cartItemList) {
            if (productMap.get(c.itemID) != null)
                cartContent.add(productMap.get(c.itemID));
        }

        dbSource.close();
        return cartContent;
    }


    public static boolean removeFromCart(Product item, Context context) {
        dbSource = new DBSource(context);
        dbSource.open();
        if (dbSource.isCartItem(item)) {
            dbSource.removeFromCart(item);
            dbSource.close();
            cartItems.remove(item);
            return true;
        }
        dbSource.close();
        return false;
    }


    public static boolean addToCart(Context context, Product item) {
        dbSource = new DBSource(context);
        dbSource.open();
        if (dbSource.isCartItem(item)) {
            return false;
        } else {
            dbSource.addCartItem(new CartItem("0", item.getId(), 1));
            dbSource.close();
            cartItems.add(item);
            return true;
        }
    }

}





