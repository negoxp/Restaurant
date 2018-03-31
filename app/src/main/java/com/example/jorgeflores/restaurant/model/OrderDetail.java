package com.example.jorgeflores.restaurant.model;

import java.util.Date;

/**
 * Created by jorgeflores on 2018-03-15.
 */

public class OrderDetail {
    public String uid;
    public ProductSize productSize;
    public Integer product_id;
    public float quantity;
    public float price;
    public float subTotal;
    public Date createdAt;

    //Constructor
    public OrderDetail(){

    }

}