package com.example.jorgeflores.restaurant;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

public class RestaurantCommentsActivity extends AppCompatActivity {
    RatingBar ratingBar;
    private FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_comments);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ratingBar=(RatingBar)findViewById(R.id.ratingBarID);
        Button btnSubmit=(Button)findViewById(R.id.btnReview);
        EditText getReview=(EditText)findViewById(R.id.getReview);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float getRating = ratingBar.getRating();
                //Toast.makeText(RestaurantCommentsActivity.this,getRating+"",Toast.LENGTH_SHORT).show();
            }
        });


    }
}
