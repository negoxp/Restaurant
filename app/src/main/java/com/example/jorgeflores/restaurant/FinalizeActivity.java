package com.example.jorgeflores.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jorgeflores.restaurant.model.Order;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FinalizeActivity extends AppCompatActivity {

    private TextView tv_card_number, tv_member_name, tv_cvv, tv_validity, mycard_number, myvalidity, mycvv, myaddress, mycity, myzip;
    private Button btnPayment;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalize);

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
}
