package com.example.jorgeflores.restaurant.model;

import java.util.Date;
import java.util.UUID;

/**
 * Created by jorgeflores on 2018-03-15.
 */

public class RestaurantComment {
    private String uid;
    public String comment;
    public Float rate;
    public String user;
    private Date createdAt;

    //Constructor
    public RestaurantComment(){
        this.uid = String.valueOf(UUID.randomUUID());
    }
}
