package com.example.jorgeflores.restaurant;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jorgeflores.restaurant.model.Order;
import com.example.jorgeflores.restaurant.model.OrderDetail;
import com.example.jorgeflores.restaurant.model.Product;
import com.example.jorgeflores.restaurant.model.ProductSize;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_checkout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        for (OrderDetail orderDetail: MainActivity.myorder.orderDetails) {

            ProductCheckoutFragment fragment = new ProductCheckoutFragment();
            Bundle arguments = new Bundle();

            Product product = findId(Integer.parseInt(String.valueOf(orderDetail.product_id)), MainActivity.products);

            arguments.putString( "id" , Integer.toString(product.id));
            arguments.putString( "title" , product.name);
            arguments.putString( "description" , product.description);
            arguments.putString( "price" , "$ "+Float.toString(product.basePrice));
            arguments.putString( "quantity" , "$ "+Float.toString(orderDetail.quantity));
            arguments.putString( "subTotal" , "$ "+Float.toString(orderDetail.subTotal));
            arguments.putString( "photo_cover" , product.photo_cover);
            fragment.setArguments(arguments);

            ft.add(R.id.fragment_container, fragment,"x_");

        }
        ft.commit();


    }
}
