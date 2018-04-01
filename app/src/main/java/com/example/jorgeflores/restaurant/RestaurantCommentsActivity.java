package com.example.jorgeflores.restaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RestaurantCommentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_comments);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
