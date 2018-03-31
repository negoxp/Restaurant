package com.example.jorgeflores.restaurant;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class ProductCheckoutFragment extends Fragment {
    private String id;

    public ProductCheckoutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View rootView = inflater.inflate(R.layout.fragment_product_checkout, container, false);
        final TextView myTitle = rootView.findViewById(R.id.title);
        final TextView myDescription = rootView.findViewById(R.id.description);
        final TextView myprice = rootView.findViewById(R.id.price);
        final TextView mysubtotal = rootView.findViewById(R.id.subtotal);
        final ImageView myImageView = rootView.findViewById(R.id.restaurantImage);
        final LinearLayout mylinearLyaout = rootView.findViewById(R.id.myfragment);

        //Set values to inputs
        Bundle arguments = getArguments();
        myTitle.setText(arguments.getString("title"));
        myDescription.setText(arguments.getString("description"));
        myprice.setText(arguments.getString("price"));
        mysubtotal.setText(arguments.getString("subTotal"));
        Glide.with(this).load(arguments.getString("photo_cover")).into(myImageView);
        id = arguments.getString("id");

        return rootView;
    }


}
