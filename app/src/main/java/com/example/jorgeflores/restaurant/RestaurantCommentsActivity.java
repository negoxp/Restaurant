package com.example.jorgeflores.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_comments);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ratingBar=(RatingBar)findViewById(R.id.ratingBarID);
        Button btnSubmit=(Button)findViewById(R.id.btnReview);

        auth = FirebaseAuth.getInstance();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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

    /*
    Menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return(super.onCreateOptionsMenu(menu));
        //return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                auth.signOut();
                Intent intentOut = new Intent(this, LoginActivity.class);
                this.startActivity(intentOut);
            case R.id.checkout:
                Intent intent1 = new Intent(this, CheckoutActivity.class);
                this.startActivity(intent1);
                return true;
            case R.id.booking:
                Intent intent2 = new Intent(this, BookingActivity.class);
                this.startActivity(intent2);
                return true;
            case R.id.rating:
                Intent intent3 = new Intent(this, RestaurantCommentsActivity.class);
                this.startActivity(intent3);
                return true;
            case R.id.contactus:
                Intent intent4 = new Intent(this, AboutUsActivity.class);
                this.startActivity(intent4);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*
    END Menu
     */
}