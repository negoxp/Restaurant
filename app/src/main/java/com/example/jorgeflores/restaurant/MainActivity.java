package com.example.jorgeflores.restaurant;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jorgeflores.restaurant.model.Product;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button signupBtn;
    private TextView txtinfo;
    private FirebaseAuth auth;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        //todo validate auth
        setContentView(R.layout.activity_main);

        txtinfo = (TextView) findViewById(R.id.infoText);

        database = FirebaseDatabase.getInstance();
        DatabaseReference restaurantsRef = database.getReference("restaurants");

        restaurantsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Product product = snapshot.getValue(Product.class);
                    Log.d("snapshot values", snapshot.toString() );

                    ProductFragment fragment = new ProductFragment();
                    Bundle arguments = new Bundle();
                    arguments.putString( "title" , product.name);
                    arguments.putString( "description" , product.description);
                    arguments.putString( "photo_cover" , product.photo_cover);
                    fragment.setArguments(arguments);

                    ft.add(R.id.fragment_container, fragment,"x_");



                }
                ft.commit();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



        //Add fragment
        /*
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        for (int x = 1; x < 15; x = x + 1) {
            //Log.i("frag","x="+x);
            RestaurantFragment fragment = new RestaurantFragment();
            Bundle arguments = new Bundle();
            arguments.putString( "title" , "my titlo");
            arguments.putString( "description" , "my titlo");
            arguments.putString( "img" , "ht");
            fragment.setArguments(arguments);

            ft.add(R.id.fragment_container, fragment,"x_"+x);
        }
        ft.commit();
        */
        //End Fragment

        // Update the Button text.
        //mButton.setText(R.string.close);

        /*

        if (auth.getCurrentUser() != null) {
            Toast.makeText(getApplicationContext(), "Enter: " + auth.getCurrentUser().getEmail() , Toast.LENGTH_SHORT).show();
            txtinfo.setText("Hola " + auth.getCurrentUser().getEmail());
        }

        database = FirebaseDatabase.getInstance();
        DatabaseReference restaurantsRef = database.getReference("restaurants");


        restaurantsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        //Restaurant restaurant = snapshot.getValue(Restaurant.class);
                        //System.out.println(restaurant.getName());

                        txtinfo.setText(snapshot.toString());
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });

        */
        signupBtn =  (Button) findViewById(R.id.signup_btn);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, Signup.class));

                //auth.signOut();
                //startActivity(new Intent(MainActivity.this, LoginActivity.class));

                // Write a message to the database
                //database = FirebaseDatabase.getInstance();
                //DatabaseReference restaurantsRef = database.getReference("restaurants");

                //txtinfo.setText("rest" + restaurantsRef.toString());

                //myRef.setValue("Hello, World!");
                /*
                String uid = auth.getCurrentUser().getUid();
                String email = auth.getCurrentUser().getEmail();

                User user = new User(uid, email);

                user.orders.add(new Order());
                //user.orders.add(new Order());
                */

                //txtinfo.setText("Value is: " + user.email);

                //myRef.child(uid).setValue(user);

                // Read from the database
                /*
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        Log.d("DEBUG", "Value is: " + value);
                        txtinfo.setText("Value is: " + value);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("DEBUG", "Failed to read value.", error.toException());
                    }
                });
                */

                // Attach a listener to read the data at our posts reference


            }
        });

    }

}
