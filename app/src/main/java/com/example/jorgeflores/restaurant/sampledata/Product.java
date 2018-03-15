package com.example.jorgeflores.restaurant.sampledata;

import java.util.Date;

/**
 * Created by jorgeflores on 2018-03-15.
 */

public class Product {
    private String uid;
    private String name;
    private String description;
    private String photo;
    private float basePrice;
    private ProductSize [] productSizes;
    private ProductAdd [] productAdds;
    private Date createdAt;

    //Constructor
    public Product(){

    }
}
