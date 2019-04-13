package com.fm.travels.bus;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import Adapter.BookingHistoryAdapter;
import model.BookingHistoryModel;

public class BingoBusBookingHistoryActivity extends AppCompatActivity implements View.OnClickListener {

    Integer star[] = {R.drawable.ic_star, R.drawable.ic_star, R.drawable.ic_star,
            R.drawable.ic_star, R.drawable.ic_star, R.drawable.ic_star};

    String rate[] = {"3.5","3.5","3.5","3.5","3.5","3.5"};

    private RecyclerView recyclerView;
    private BookingHistoryAdapter bookingHistoryAdapter;
    private ArrayList<BookingHistoryModel> bookingHistoryModels;

    Animation animation;
    //bottombar
    LinearLayout linear1, linear2, linear3, linear4;
    ImageView bus, discount, checked, user;
    TextView search, offer, booking, profile;

    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingo_bus_booking_history);


        recyclerView = (RecyclerView) findViewById(R.id.booking_history);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BingoBusBookingHistoryActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        bookingHistoryModels = new ArrayList<>();

        for (int i = 0; i < star.length; i++) {
            BookingHistoryModel ab = new BookingHistoryModel(star[i],rate[i]);
            bookingHistoryModels.add(ab);
        }
        bookingHistoryAdapter = new BookingHistoryAdapter(BingoBusBookingHistoryActivity.this, bookingHistoryModels);
        recyclerView.setAdapter(bookingHistoryAdapter);
        recyclerView.setFocusableInTouchMode(false);
        recyclerView.setNestedScrollingEnabled(false);
        runAnimation(recyclerView);
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

        checked.setImageResource(R.drawable.ic_bookings_blue);
        booking.setTextColor(Color.parseColor("#000000"));
    }

    private void runAnimation(RecyclerView recyclerView) {
        Context context = recyclerView.getContext();
        LayoutAnimationController controller = null;

        controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fall_down);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.liner1:
                bus.setImageResource(R.drawable.ic_bus_blue);
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
                break;
        }
    }
}
