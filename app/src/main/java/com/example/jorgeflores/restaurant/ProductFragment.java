package com.example.jorgeflores.restaurant;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class ProductFragment extends Fragment {
    private String id;

    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View rootView = inflater.inflate(R.layout.fragment_product, container, false);
        final TextView myTitle = rootView.findViewById(R.id.title);
        final TextView myDescription = rootView.findViewById(R.id.description);
        final ImageView myImageView = rootView.findViewById(R.id.restaurantImage);
        final LinearLayout mylinearLyaout = rootView.findViewById(R.id.myfragment);

        //Set values to inputs
        Bundle arguments = getArguments();
        myTitle.setText(arguments.getString("title"));
        myDescription.setText(arguments.getString("description"));
        Glide.with(this).load(arguments.getString("photo_cover")).into(myImageView);
        id = arguments.getString("id");


        mylinearLyaout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ProductSelectedActivity.class);
                i.putExtra("id", id);
                startActivity(i);
            }
        });


        return rootView;

        //return inflater.inflate(R.layout.fragment_product, container, false);
    }

    @NonNull
    public static ProductFragment newInstance() {
        return new ProductFragment();
    }


}
