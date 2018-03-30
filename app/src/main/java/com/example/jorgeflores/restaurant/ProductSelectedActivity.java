package com.example.jorgeflores.restaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.jorgeflores.restaurant.model.Product;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ProductSelectedActivity extends AppCompatActivity {
    private String id;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_selected);

        Bundle b = getIntent().getExtras();
        id = b.getString("id");

        final TextView myid = findViewById(R.id.productid);

        myid.setText(id);

        database = FirebaseDatabase.getInstance();
        DatabaseReference restaurantsRef = database.getReference("restaurants");
        Query first = restaurantsRef.orderByChild("id").equalTo(id);

        myid.setText(first.toString());


        FirebaseDatabase.getInstance().getReference("restaurants")
                .orderByChild("id")
                .equalTo(id)
                .limitToFirst(1)
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        //Product product = dataSnapshot.getValue(Product.class);
                        //listener.onUserRetrieved(user);
                        //myid.setText(dataSnapshot.toString());
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });



    }
}
