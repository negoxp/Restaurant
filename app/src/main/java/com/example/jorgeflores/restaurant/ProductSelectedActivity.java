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

import java.util.ArrayList;

public class ProductSelectedActivity extends AppCompatActivity {
    private String id;
    private FirebaseDatabase database;

    public Product findId(int id, ArrayList<Product> al) {

        for(Product p : al) {
            if(p.id == id)
                return p;
        }

        return new Product();
    }

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

        //myid.setText(first.toString());

        Product product = findId(1, MainActivity.products);

        myid.setText(product.name);





    }
}
