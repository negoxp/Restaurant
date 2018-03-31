package com.example.jorgeflores.restaurant.model;

import com.google.firebase.database.Exclude;

import java.util.Date;

/**
 * Created by jorgeflores on 2018-03-15.
 */

public class ProductSize {
    @Exclude

    private Integer id;
    public Integer products_id;
    public float price;
    public String size;
    private Date created_at;
    private Date updated_at;

    //Constructor
    public ProductSize(){

    }

}
