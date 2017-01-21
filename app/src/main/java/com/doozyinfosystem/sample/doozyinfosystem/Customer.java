package com.doozyinfosystem.sample.doozyinfosystem;

import android.os.Parcel;
import android.os.Parcelable;

public class Customer implements Parcelable {
    public static final Parcelable.Creator<Customer> CREATOR = new Parcelable.Creator<Customer>() {
        @Override
        public Customer createFromParcel(Parcel source) {
            return new Customer(source);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };
    private String customerID;
    private String customerName;
    private String customerEmail;
    private String customerPassword;
    private String customerAddress;
    private String customerPhone;

    public Customer(String customerID, String customerName, String customerEmail, String customerPassword, String customerAddress, String customerPhone) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPassword = customerPassword;
        this.customerAddress = customerAddress;
        this.customerPhone = customerPhone;
    }

    protected Customer(Parcel in) {
        this.customerID = in.readString();
        this.customerName = in.readString();
        this.customerEmail = in.readString();
        this.customerPhone = in.readString();
        this.customerAddress = in.readString();
        this.customerPassword = in.readString();
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.customerID);
        dest.writeString(this.customerName);
        dest.writeString(this.customerEmail);
        dest.writeString(this.customerPhone);
        dest.writeString(this.customerAddress);
        dest.writeString(this.customerPassword);
    }
}
