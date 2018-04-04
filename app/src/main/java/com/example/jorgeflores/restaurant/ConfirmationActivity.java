package com.example.jorgeflores.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class ConfirmationActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        auth = FirebaseAuth.getInstance();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Button keepshoping = (Button) findViewById(R.id.keepshopping);

        keepshoping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ConfirmationActivity.this, MainActivity.class);
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
