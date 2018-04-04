package com.example.jorgeflores.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.jorgeflores.restaurant.model.Booking;
import com.example.jorgeflores.restaurant.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BookingActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        auth = FirebaseAuth.getInstance();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Button btnReserved = (Button) findViewById(R.id.btnReserved);
        final DatePicker datePicker2 = (DatePicker) findViewById(R.id.datePicker2);
        final TimePicker timePicker2 = (TimePicker) findViewById(R.id.timePicker2);
        final EditText numberOfGuests = (EditText) findViewById(R.id.numberOfGuests);

        btnReserved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Booking mybooking = new Booking();

                SimpleDateFormat dateformat = new SimpleDateFormat("YY-MM-dd");

                mybooking.date = dateformat.format(new Date(datePicker2.getYear(), datePicker2.getMonth(), datePicker2.getDayOfMonth()));
                mybooking.time = timePicker2.getCurrentHour().toString() + ":" +timePicker2.getCurrentMinute().toString();
                mybooking.guess_numeber = numberOfGuests.getText().toString();
                //private User user;
                //private Date createdAt;

                database =  FirebaseDatabase.getInstance();
                DatabaseReference mRef =  database.getReference().child("Bookings").push();
                mRef.setValue(mybooking);

                Toast.makeText(getApplicationContext(), "Booked!!", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(BookingActivity.this, MainActivity.class);
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
