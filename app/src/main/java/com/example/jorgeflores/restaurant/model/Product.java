package com.example.jorgeflores.restaurant.model;

import java.util.Date;

/**
 * Created by jorgeflores on 2018-03-15.
 */

public class Product {
    private String uid;
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
