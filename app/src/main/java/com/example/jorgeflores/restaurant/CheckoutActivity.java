package com.example.jorgeflores.restaurant;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jorgeflores.restaurant.model.Order;
import com.example.jorgeflores.restaurant.model.OrderDetail;
import com.example.jorgeflores.restaurant.model.Product;
import com.example.jorgeflores.restaurant.model.ProductSize;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity {

    public Product findId(int id, ArrayList<Product> al) {
        for(Product p : al) {
            if(p.id == id)
                return p;
        }
        return new Product();
    }

    private float subtotal =0;
    private float taxes =0;
    private float total =0;
    private Button btnPay;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        final TextView productSubtotal = findViewById(R.id.productsSubtotal);
        final TextView productTaxes = findViewById(R.id.productsTaxes);
        final TextView productTotal = findViewById(R.id.productTotal);
        final Button btnPay =(Button) findViewById(R.id.payBtn);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final Button keepshoping = (Button) findViewById(R.id.keepshoppingBtn);

        keepshoping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CheckoutActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        for (OrderDetail orderDetail: MainActivity.myorder.orderDetails) {

            ProductCheckoutFragment fragment = new ProductCheckoutFragment();
            Bundle arguments = new Bundle();

            Product product = findId(Integer.parseInt(String.valueOf(orderDetail.product_id)), MainActivity.products);

            arguments.putString( "id" , Integer.toString(product.id));
            arguments.putString( "title" , product.name);
            arguments.putString( "description" , product.description);
            arguments.putString( "price" , "$ "+String.format("%.2f",orderDetail.price));
            arguments.putString( "quantity" , String.format("%.0f",orderDetail.quantity));
            arguments.putString( "subTotal" , "$ "+String.format("%.2f",orderDetail.subTotal));
            arguments.putString( "photo_cover" , product.photo_cover);
            fragment.setArguments(arguments);

            subtotal += orderDetail.subTotal;


            ft.add(R.id.fragment_container, fragment,"x_");

        }

        taxes = subtotal * Float.parseFloat("0.075");
        total = subtotal + taxes;

        MainActivity.myorder.subTotal = subtotal;
        MainActivity.myorder.tax = (float) (taxes);
        MainActivity.myorder.total = total;

        productSubtotal.setText("$ "+String.format("%.2f", subtotal));
        productTaxes.setText("$ "+String.format("%.2f", taxes));
        productTotal.setText("$ "+String.format("%.2f", total));

        ft.commit();


        //Record on firebase on click btnpay
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.myorder.status="Ordered";

                database =  FirebaseDatabase.getInstance();
                DatabaseReference mRef =  database.getReference().child("Orders").push();
                mRef.setValue(MainActivity.myorder);

                MainActivity.myorder = new Order();

                Intent i = new Intent(CheckoutActivity.this, ConfirmationActivity.class);
                startActivity(i);
            }
        });


    }
}
