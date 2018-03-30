package com.example.jorgeflores.restaurant.model;

import com.google.firebase.database.Exclude;

import java.util.Date;

/**
 * Created by jorgeflores on 2018-03-15.
 */

public class Product {
    @Exclude

    private String uid;
    public Integer id;
    public String name;
    public String description;
    public String photo_cover;
    public float basePrice;
    private ProductSize [] productSizes;
    private ProductAdd [] productAdds;
    private Date createdAt;

    //Constructor
    public Product(){

    }
}
