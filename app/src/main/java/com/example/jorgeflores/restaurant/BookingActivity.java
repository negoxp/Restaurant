package com.example.jorgeflores.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.jorgeflores.restaurant.model.Booking;
import com.example.jorgeflores.restaurant.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BookingActivity extends AppCompatActivity {
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

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
}
