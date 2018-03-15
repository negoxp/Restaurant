package com.example.jorgeflores.restaurant.sampledata;

import java.util.Date;

/**
 * Created by jorgeflores on 2018-03-15.
 */

public class Restaurant {
    private String uid;
    private String name;
    private String description;
    private String photo_cover;
    private String logo;
    private String address;
    private String open_hours;
    private float rateAvg;
    private String email;
    private String phone;
    private Product [] products;
    private RestaurantComment [] restaurantComments;
    private Date createdAt;

    //Constructor
    public Restaurant(){

    }
}
