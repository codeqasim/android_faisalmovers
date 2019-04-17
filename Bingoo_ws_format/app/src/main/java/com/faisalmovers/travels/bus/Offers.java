package com.faisalmovers.travels.bus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Adapter.OffersAdapter;
import model.OffersModel;

public class Offers extends AppCompatActivity implements View.OnClickListener {

    Integer poster[] = {R.drawable.banner1, R.drawable.banner2, R.drawable.banner3, R.drawable.banner4};

    private RecyclerView recyclerView;
    private OffersAdapter offersAdapter;
    private ArrayList<OffersModel> offersModel;

    LinearLayout linear1, linear2, linear3, linear4;

    ImageView bus, discount, checked, user;
    TextView search, offer, booking, profile;

    Context context=this ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);

        recyclerView = (RecyclerView) findViewById(R.id.offers);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(Offers.this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        offersModel = new ArrayList<>();

        for (int i = 0; i < poster.length; i++) {
            OffersModel ab = new OffersModel(poster[i]);
            offersModel.add(ab);
        }
        offersAdapter = new OffersAdapter(Offers.this, offersModel);
        recyclerView.setAdapter(offersAdapter);



        //        custome bottombar
        linear1 = (LinearLayout) findViewById(R.id.liner1);
        linear2 = (LinearLayout) findViewById(R.id.liner2);
        linear3 = (LinearLayout) findViewById(R.id.liner3);
        linear4 = (LinearLayout) findViewById(R.id.liner4);


        bus = (ImageView) findViewById(R.id.bus);
        discount = (ImageView) findViewById(R.id.discount);
        checked = (ImageView) findViewById(R.id.checked);
        user = (ImageView) findViewById(R.id.user);

        search = (TextView) findViewById(R.id.search);
        offer = (TextView) findViewById(R.id.offer);
        booking = (TextView) findViewById(R.id.booking);
        profile = (TextView) findViewById(R.id.profile);

        linear1.setOnClickListener(this);
        linear2.setOnClickListener(this);
        linear3.setOnClickListener(this);
        linear4.setOnClickListener(this);



        bus.setImageResource(R.drawable.ic_bus_gray);
        discount.setImageResource(R.drawable.ic_percentage_blue);
        checked.setImageResource(R.drawable.ic_bookings_gray);
        user.setImageResource(R.drawable.ic_profile_gray);

        search.setTextColor(Color.parseColor("#91959d"));
        offer.setTextColor(Color.parseColor("#000000"));
        booking.setTextColor(Color.parseColor("#91959d"));
        profile.setTextColor(Color.parseColor("#91959d"));

    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.liner1:
                bus.setImageResource(R.drawable.ic_bus_gray);
                discount.setImageResource(R.drawable.ic_percentage_gray);
                checked.setImageResource(R.drawable.ic_bookings_gray);
                user.setImageResource(R.drawable.ic_profile_gray);

                search.setTextColor(Color.parseColor("#000000"));
                offer.setTextColor(Color.parseColor("#91959d"));
                booking.setTextColor(Color.parseColor("#91959d"));
                profile.setTextColor(Color.parseColor("#91959d"));

                Intent i = new Intent(context, Bingobus24Activity.class);
                context.startActivity(i);
                break;

            case R.id.liner2:
                bus.setImageResource(R.drawable.ic_bus_gray);
                discount.setImageResource(R.drawable.ic_percentage_blue);
                checked.setImageResource(R.drawable.ic_bookings_gray);
                user.setImageResource(R.drawable.ic_profile_gray);

                search.setTextColor(Color.parseColor("#91959d"));
                offer.setTextColor(Color.parseColor("#000000"));
                booking.setTextColor(Color.parseColor("#91959d"));
                profile.setTextColor(Color.parseColor("#91959d"));


                //Intent intent = new Intent(getApplicationContext(),Offers.class);
                //startActivity(intent);


                break;

            case R.id.liner3:
                bus.setImageResource(R.drawable.ic_bus_gray);
                discount.setImageResource(R.drawable.ic_percentage_gray);
                checked.setImageResource(R.drawable.ic_bookings_blue);
                user.setImageResource(R.drawable.ic_profile_gray);

                search.setTextColor(Color.parseColor("#91959d"));
                offer.setTextColor(Color.parseColor("#91959d"));
                booking.setTextColor(Color.parseColor("#000000"));
                profile.setTextColor(Color.parseColor("#91959d"));

                Intent home = new Intent(context, BingoBusBookingHistoryActivity.class);
                context.startActivity(home);
                break;

            case R.id.liner4:
                bus.setImageResource(R.drawable.ic_bus_gray);
                discount.setImageResource(R.drawable.ic_percentage_gray);
                checked.setImageResource(R.drawable.ic_bookings_gray);
                user.setImageResource(R.drawable.ic_profile_blue);

                search.setTextColor(Color.parseColor("#91959d"));
                offer.setTextColor(Color.parseColor("#91959d"));
                booking.setTextColor(Color.parseColor("#91959d"));
                profile.setTextColor(Color.parseColor("#000000"));

                Intent Profile = new Intent(context,ProfilePerson.class);
                context.startActivity(Profile);
                break;


        }
    }
}
