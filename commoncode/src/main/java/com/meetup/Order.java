package com.meetup;

/**
 * Created by Sunny on 12-04-2016.
 */
public class Order {
    public Order() {
    }

    private String itemName;
    private int itemPrice;
    private String city;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Order(String itemName, int itemPrice, String city) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.city = city;
    }
}
