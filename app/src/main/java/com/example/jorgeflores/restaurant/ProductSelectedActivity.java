package com.example.jorgeflores.restaurant;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

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
    private Button keepshoppingBtn;
    private Integer extraSize = 0;
    private Integer quantity = 1;
    private float extra =0 ;
    private RadioButton lastBtn;
    private Switch dswitch;
    private FirebaseAuth auth;

    public Product findId(int id, ArrayList<Product> al) {
        for(Product p : al) {
            if(p.id == id)
                return p;
        }
        return new Product();
    }
    private AddtoCartListener onClickBtnListener = new AddtoCartListener();

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_selected);

        auth = FirebaseAuth.getInstance();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Var declaration
        productView = findViewById(R.id.productView);
        productPrice = findViewById(R.id.productPrice);
        productTotal = findViewById(R.id.productTotal);
        productQuantity = findViewById(R.id.productQuantity);
        checkoutBtn = findViewById(R.id.checkoutBtn);
        keepshoppingBtn = findViewById(R.id.keepshoppingBtn);
        addCart = findViewById(R.id.addCart);

        //Recieve info from pass activity
        Bundle b = getIntent().getExtras();
        id = b.getString("id");

        //Get product info
        product = findId(Integer.parseInt(id), MainActivity.products);
        productPrice.setText("$ "+Float.toString(product.basePrice));
        productTotal.setText("$ "+Float.toString(product.basePrice * quantity ));
        Glide.with(this).load(product.photo_cover.toString()).into(productView);

        /*
        Product extras
         */
        LinearLayout extrasl = (LinearLayout) findViewById(R.id.extrasLayout);
        extrasl.removeAllViews();
        for (ProductAdd productadd: MainActivity.productAdds) {
            if(product.id == productadd.products_id) {
                dswitch = new Switch(this);
                dswitch.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                dswitch.setPadding(11, 11, 11, 11);
                dswitch.setTextColor(R.color.inputs);
                dswitch.setText(productadd.name_add + "      $ "+Float.toString(productadd.price));
                dswitch.setTextSize(18);
                extrasl.addView(dswitch);
            }
        }

        /*
        Product Size
         */
        RadioGroup sizesGroup = (RadioGroup) findViewById(R.id.radioGroupSize);
        sizesGroup.removeAllViews();
        for (ProductSize productsize: MainActivity.productSizes) {
            if(product.id == productsize.products_id) {
                RadioButton rButton = new RadioButton(this);
                rButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                rButton.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                rButton.setTextColor(R.color.inputs);
                rButton.setTextSize(18);
                rButton.setText(productsize.size+ "      $ "+String.format("%.2f",productsize.price));
                sizesGroup.addView(rButton);
                lastBtn = rButton;
            }
        }
        lastBtn.setChecked(true);

        /*
         Listeners
        */
        productQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String value = productQuantity.getText().toString();
                if (!TextUtils.isEmpty(value)) {
                    quantity = Integer.parseInt(value);
                    productTotal.setText("$ "+String.format("%.2f",(product.basePrice+extraSize+extra) * quantity ));
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });

        dswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            //todo - recover extras from firebase
            if (isChecked){
                extra = Float.parseFloat("0.99");
                productTotal.setText("$ "+String.format("%.2f",(product.basePrice+extraSize+extra) * quantity ));
            }else{
                extra = Float.parseFloat("0");
                productTotal.setText("$ "+String.format("%.2f",(product.basePrice+extraSize+extra) * quantity ));
            }
            }
        });

        sizesGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
            RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);
            View radioButton = group.findViewById(checkedId);
            int index = group.indexOfChild(radioButton);
            //todo - recover extra size from firebase
            switch (index)
            {
                case 0:
                    extraSize=2;
                    productTotal.setText("$ "+String.format("%.2f",(product.basePrice +extraSize+extra) * quantity));
                    break;
                case 1:
                    extraSize=1;
                    productTotal.setText("$ "+String.format("%.2f",(product.basePrice+extraSize+extra) * quantity));
                    break;
                case 2:
                    extraSize=0;
                    productTotal.setText("$ "+String.format("%.2f",(product.basePrice+extraSize+extra) * quantity));
                    break;
            }
            }
        });

        /*
        Buttons actions
         */
        addCart.setOnClickListener(onClickBtnListener);
        checkoutBtn.setOnClickListener(onClickBtnListener);
        keepshoppingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductSelectedActivity.this, MainActivity.class));
            }
        });
    }

    public class AddtoCartListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            OrderDetail myOrderDetail = new OrderDetail();
            myOrderDetail.productSize = new ProductSize();
            myOrderDetail.quantity = quantity;
            myOrderDetail.price =(product.basePrice + extraSize + extra);
            myOrderDetail.product_id = product.id;
            myOrderDetail.subTotal=myOrderDetail.price * quantity;
            MainActivity.myorder.orderDetails.add(myOrderDetail);

            Intent i = new Intent(ProductSelectedActivity.this, CheckoutActivity.class);
            startActivity(i);
        }

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
