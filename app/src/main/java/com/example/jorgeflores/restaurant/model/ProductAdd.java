package com.example.jorgeflores.restaurant.model;

import com.google.firebase.database.Exclude;

import java.util.Date;

/**
 * Created by jorgeflores on 2018-03-15.
 */

public class ProductAdd {
    @Exclude

    public Integer id;
    public String name_add;
    public float price;
    public Integer products_id;
    private Date created_at;
    private Date updated_at;

    //Constructor
    public ProductAdd(){

    }
}
