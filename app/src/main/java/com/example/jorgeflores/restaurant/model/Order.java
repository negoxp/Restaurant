package com.example.jorgeflores.restaurant.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by jorgeflores on 2018-03-15.
 */

public class Order {

    public String uid;
    public float subTotal;
    public float tax;
    public float total;
    public String status;

    public String cardnumber;
    public String validity;
    public String cvv;
    public String address;
    public String city;
    public String zip;

    public ArrayList<OrderDetail> orderDetails;
    public Date createdAt;
    /*
    private OrderDetail [] orderDetails;
    */

    //Constructor
    public Order(){
        //this.uid = UUID.randomUUID().toString();
        this.uid = String.valueOf(UUID.randomUUID());
        this.subTotal=0;
        this.tax=0;
        this.total=0;
        this.status="open";

        this.orderDetails = new ArrayList<OrderDetail>();
    }


}
