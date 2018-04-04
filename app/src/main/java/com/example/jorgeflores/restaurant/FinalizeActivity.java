package com.example.jorgeflores.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jorgeflores.restaurant.model.Order;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FinalizeActivity extends AppCompatActivity {

    private TextView tv_card_number, tv_member_name, tv_cvv, tv_validity, mycard_number, myvalidity, mycvv, myaddress, mycity, myzip;
    private Button btnPayment;
    private FirebaseDatabase database;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalize);

        auth = FirebaseAuth.getInstance();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv_card_number = findViewById(R.id.tv_card_number);
        tv_member_name = findViewById(R.id.tv_member_name);
        tv_cvv = findViewById(R.id.tv_cvv);
        tv_validity = findViewById(R.id.tv_validity);
        mycard_number = findViewById(R.id.mycard_number);
        myvalidity = findViewById(R.id.myvalidity);
        mycvv = findViewById(R.id.mycvv);
        btnPayment = findViewById(R.id.paybtn);

        myaddress = findViewById(R.id.myaddress);
        mycity = findViewById(R.id.mycity);
        myzip = findViewById(R.id.myzip);


        mycard_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                String value = s.toString();
                tv_card_number.setText(value);
            }
        });

        myvalidity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                String value = s.toString();
                tv_validity.setText(value);
            }
        });

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.myorder.status="Paid";

                MainActivity.myorder.cardnumber=mycard_number.getText().toString();
                MainActivity.myorder.validity=myvalidity.getText().toString();
                MainActivity.myorder.cvv=mycvv.getText().toString();
                MainActivity.myorder.address=myaddress.getText().toString();
                MainActivity.myorder.city=mycity.getText().toString();
                MainActivity.myorder.zip=myzip.getText().toString();
                
                database =  FirebaseDatabase.getInstance();
                DatabaseReference mRef =  database.getReference().child("Orders").push();
                mRef.setValue(MainActivity.myorder);
                MainActivity.myorder = new Order();

                Intent i = new Intent(FinalizeActivity.this, ConfirmationActivity.class);
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
