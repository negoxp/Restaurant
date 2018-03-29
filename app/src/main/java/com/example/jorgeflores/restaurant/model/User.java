package com.example.jorgeflores.restaurant.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jorgeflores on 2018-03-15.
 */

public class User {

    public String uid;
    public String name;
    public String email;
    public String mobile;
    public String address;
    public String city;
    public String state;
    public String country;
    public ArrayList<Order> orders;
    public Date createdAt;



    //Constructor
    public User(String uid, String email) {
        this.uid = uid;
        this.name = "";
        this.email = email;
        orders = new ArrayList<Order>();
    }

    /* SET methods */
    public void setUid(String uid){
        this.uid = uid;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    /*
    public void setMobile(String mobile){
        this.mobile = mobile;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setCity (String city){
        this.city = city;
    }
    public void setState(String state){
        this.state = state;
    }
    public void setCountry(String country){
        this.country = country;
    }
    /* GET methods */
    public String getUid(){
        return this.uid;
    }
    public String getName(){
        return this.name;
    }
    public String getEmail(){
        return this.email;
    }
    /*
    public String getMobile(){
        return this.mobile;
    }
    public String getAddress(){
        return this.address;
    }
    public String getCity (){
        return this.city;
    }
    public String getState(){
        return this.state;
    }
    public String getCountry(){
        return this.country;
    }
    */

}
