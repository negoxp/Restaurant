package com.example.jorgeflores.restaurant.model;

import java.util.Date;

/**
 * Created by jorgeflores on 2018-03-15.
 */

public class OrderDetail {
    private String uid;
    private ProductSize productSize;
    private float quantity;
    private float price;
    private float subTotal;
    private Date createdAt;

    //Constructor
    public OrderDetail(){

    }

}