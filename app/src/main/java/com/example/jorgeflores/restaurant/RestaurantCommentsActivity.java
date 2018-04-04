package com.example.jorgeflores.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.jorgeflores.restaurant.model.Booking;
import com.example.jorgeflores.restaurant.model.RestaurantComment;
import com.example.jorgeflores.restaurant.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RestaurantCommentsActivity extends AppCompatActivity {
    RatingBar ratingBar;
    private Button btnReview;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_comments);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ratingBar=(RatingBar)findViewById(R.id.ratingBarID);
        Button btnSubmit=(Button)findViewById(R.id.btnReview);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText getReview=(EditText)findViewById(R.id.getReview);
                float getRating = ratingBar.getRating();
                //Toast.makeText(RestaurantCommentsActivity.this,getRating+"",Toast.LENGTH_SHORT).show();


                FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
                //Toast.makeText(this, "" + currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();


                RestaurantComment myrestaurantc = new RestaurantComment();

                myrestaurantc.comment= getReview.getText().toString();
                myrestaurantc.rate=getRating;
                myrestaurantc.user = currentFirebaseUser.getUid().toString();


                database =  FirebaseDatabase.getInstance();
                DatabaseReference mRef =  database.getReference().child("Comments").push();
                mRef.setValue(myrestaurantc);

                Toast.makeText(getApplicationContext(), "Thanks!!", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(RestaurantCommentsActivity.this, MainActivity.class);
                startActivity(i);



            }
        });
    }
}