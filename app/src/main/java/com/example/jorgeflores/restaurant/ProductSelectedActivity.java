package com.example.jorgeflores.restaurant;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jorgeflores.restaurant.model.OrderDetail;
import com.example.jorgeflores.restaurant.model.Product;
import com.example.jorgeflores.restaurant.model.ProductAdd;
import com.example.jorgeflores.restaurant.model.ProductSize;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class ProductSelectedActivity extends AppCompatActivity {
    private String id;
    private FirebaseDatabase database;
    private ImageView productView;
    private TextView productPrice;
    private TextView productTotal;
    private EditText productQuantity;
    private Product product;
    private Button addCart;
    private Button checkoutBtn;

    public Product findId(int id, ArrayList<Product> al) {
        for(Product p : al) {
            if(p.id == id)
                return p;
        }
        return new Product();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_selected);

        productView = findViewById(R.id.productView);
        productPrice = findViewById(R.id.productPrice);
        productTotal = findViewById(R.id.productTotal);
        productQuantity = findViewById(R.id.productQuantity);
        checkoutBtn = findViewById(R.id.checkoutBtn);
        addCart = findViewById(R.id.addCart);

        Bundle b = getIntent().getExtras();
        id = b.getString("id");

        //final TextView myid = findViewById(R.id.productid);
        Float tquantity = Float.parseFloat( productQuantity.getText().toString() );

        database = FirebaseDatabase.getInstance();
        DatabaseReference restaurantsRef = database.getReference("restaurants");

        product = findId(Integer.parseInt(id), MainActivity.products);
        productPrice.setText("$ "+Float.toString(product.basePrice));
        productTotal.setText("$ "+Float.toString(product.basePrice * tquantity ));
        Glide.with(this).load(product.photo_cover.toString()).into(productView);

        for (ProductAdd productadd: MainActivity.productAdds) {
            if(product.id == productadd.products_id) {
                LinearLayout extrasl = (LinearLayout) findViewById(R.id.extrasLayout);
                Switch dswitch = new Switch(this);
                dswitch.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                dswitch.setPadding(11, 11, 11, 11);
                dswitch.setText(productadd.name_add + "      $ "+Float.toString(productadd.price));
                dswitch.setTextSize(18);
                extrasl.addView(dswitch);
            }
        }

        for (ProductSize productsize: MainActivity.productSizes) {
            RadioGroup sizesGroup = (RadioGroup) findViewById(R.id.radioGroupSize);
            if(product.id == productsize.products_id) {
                RadioButton rButton = new RadioButton(this);
                rButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                rButton.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                rButton.setText(productsize.size+ "      $ "+Float.toString(productsize.price));
                sizesGroup.addView(rButton);
            }
        }

        // Listener

        productQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String value = productQuantity.getText().toString();
                if (!TextUtils.isEmpty(value)) {
                    Float tquantity = Float.parseFloat( value );
                    productTotal.setText("$ "+Float.toString(product.basePrice * tquantity ));
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });



        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //MainActivity.myorder;
                Float tquantity = Float.parseFloat( productQuantity.getText().toString() );

                OrderDetail myOrderDetail = new OrderDetail();
                myOrderDetail.uid="sda";
                myOrderDetail.productSize = new ProductSize();
                myOrderDetail.quantity = tquantity;
                myOrderDetail.price =product.basePrice;
                myOrderDetail.product_id = product.id;
                myOrderDetail.subTotal=product.basePrice * tquantity;

                //Update order
                MainActivity.myorder.subTotal = product.basePrice * tquantity;
                MainActivity.myorder.tax = (float) (product.basePrice * tquantity * 0.075);
                MainActivity.myorder.total = MainActivity.myorder.subTotal + MainActivity.myorder.tax;
                MainActivity.myorder.orderDetails.add(myOrderDetail);

                Intent i = new Intent(ProductSelectedActivity.this, CheckoutActivity.class);
                //i.putExtra("id", id);
                startActivity(i);


                //user.orders.add(new Order());

                database =  FirebaseDatabase.getInstance();
                DatabaseReference mRef =  database.getReference().child("Orders").push();
                mRef.setValue(MainActivity.myorder);



            }
        });


        //myid.setText(product.name);





    }
}
