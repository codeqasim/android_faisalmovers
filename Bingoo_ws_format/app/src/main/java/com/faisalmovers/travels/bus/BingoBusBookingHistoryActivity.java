package com.faisalmovers.travels.bus;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import Adapter.Bingobus7Adapter;
import Adapter.BookingHistoryAdapter;
import model.Bingobus7Model;
import model.BoardingPoints;
import model.BookingHistoryModel;
import util.Url;
import util.Utils;

public class BingoBusBookingHistoryActivity extends Url implements View.OnClickListener {

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

    ArrayList<BookingHistoryModel> historyload = new ArrayList<>();

    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    ProgressBar progressBar2 ;
    SharedPreferences pref;
    private boolean inProgress = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingo_bus_booking_history);




        recyclerView = (RecyclerView) findViewById(R.id.booking_history);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BingoBusBookingHistoryActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);






        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        String id =pref.getString("id"," ");
        loadhistorydata(yourbookinghistory+id+"&lang=en" );



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

                Intent Offer = new Intent(context, Offers.class);
                context.startActivity(Offer);

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

                Intent Profile = new Intent(context,ProfilePerson.class);
                context.startActivity(Profile);
                break;
        }
    }
    @Override
    public void onBackPressed() {

       // dailogbox();
        finish();

    }

    @SuppressLint("ResourceAsColor")
    public  void dailogbox ()
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure, You wanted to Exit");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        //  Toast.makeText(getApplicationContext(),"You clicked yes button",Toast.LENGTH_LONG).show();
                        finish();
                        finishAffinity();
                        System.exit(0);

                    }
                });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(R.color.fm);
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(R.color.fm);
    }



    public void  loadhistorydata2()
    {

        String history =" ";//pref.getString("history"," ");
        Log.d("historyhistory"," ;"+history);

        try {


            JSONArray jsonArray = new JSONArray(history);

            for (int i =0; i < jsonArray.length();i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Bingobus7Model busModel = new Bingobus7Model();

                String nameoperator = jsonObject.getString("operator");
                busModel.setOperator(nameoperator);

                busModel.setFromCity(jsonObject.getString("fromCity"));
                busModel.setToCity(jsonObject.getString("toCity"));
                busModel.setBusType(jsonObject.getString("busType"));
                busModel.setDatetime(jsonObject.getString("datetime"));
            //    historyload.add(busModel);


            }

            Log.d("ccccc",historyload.size()+"  ");

           /* bookingHistoryModels = new ArrayList<>();

            for (int i = 0; i < star.length; i++) {
                BookingHistoryModel ab = new BookingHistoryModel(star[i],rate[i]);
                bookingHistoryModels.add(ab);
            }*/
       /*    bookingHistoryAdapter = new BookingHistoryAdapter(BingoBusBookingHistoryActivity.this, historyload);
            recyclerView.setAdapter(bookingHistoryAdapter);
            recyclerView.setFocusableInTouchMode(false);
            recyclerView.setNestedScrollingEnabled(false);
            runAnimation(recyclerView);*/

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void  loadhistorydata(String url)
    {


        inProgress=true;
        mRequestQueue = Volley.newRequestQueue(this);
        mStringRequest = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {

                Log.d("fromCityIdfromCityId2",response);
                int size =0;
                try {
                    JSONObject  parentObject = new JSONObject(response.toString());
                    JSONObject error = parentObject.getJSONObject("error");
                    Log.d("errorleghtbb",error.length()+" ");


                    Boolean status = error.getBoolean("status");

                    if(status==true)
                    {
                      //  progressBar2.setVisibility(View.GONE);
                        Utils.showInfoToast(getApplicationContext() ,error.getString("msg"));
                    }



                    JSONArray main_object = parentObject.getJSONArray("response");




                    if(main_object.length() >=50)
                    {
                       // Log.d("fromCityIdfromCityId2length",main_object.length()+" ");
                        size =50;
                    }else {
                        size = main_object.length();
                    }

                    for(int i=0;i<size;i++)
                    {
                        JSONObject jsonObject=main_object.getJSONObject(i);
                        BookingHistoryModel  busModel = new BookingHistoryModel();


                        String  id = jsonObject.getString("bookingID");
                        String title=jsonObject.getString("title");
                        String origin = jsonObject.getString("origin");
                        String destination=jsonObject.getString("destination");
                        String bookingDate=jsonObject.getString("bookingDate");
                        String  busTime=jsonObject.getString("busTime");

                        busModel.setId(id);
                        busModel.setTitle(title);
                        busModel.setOrigin(origin);
                        busModel.setDestination(destination);
                        busModel.setBookingDate(bookingDate);
                        busModel.setBusTime(busTime);

                        historyload.add(busModel);

                    }


                    progressBar2.setVisibility(View.GONE);
                    bookingHistoryAdapter = new BookingHistoryAdapter(BingoBusBookingHistoryActivity.this, historyload);
                    recyclerView.setAdapter(bookingHistoryAdapter);
                    recyclerView.setFocusableInTouchMode(false);
                    recyclerView.setNestedScrollingEnabled(false);
                    runAnimation(recyclerView);

                    Log.d("fromCityIdfromCityId2",historyload.size()+" ");



                    inProgress=false;
                } catch (JSONException e) {

                    Log.d("abcwwwwd", e.getMessage());
                   progressBar2.setVisibility(View.GONE);
                    inProgress=false;
                    //Utils.showInfoToast(getApplicationContext() ,"Bookings not found");
                }




            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                progressBar2.setVisibility(View.GONE);
                Utils.showInfoToast(getApplicationContext() ,"no data avaible for this way");
                //dateinfo.setText(businfo.size() +" Buses Found for "+datetime );
            //    responmessage = String.valueOf(error);
            }
        });
        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(mStringRequest);


    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!inProgress)
            return super.dispatchTouchEvent(ev);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        bus.setImageResource(R.drawable.ic_bus_gray);
        discount.setImageResource(R.drawable.ic_percentage_gray);
        checked.setImageResource(R.drawable.ic_bookings_blue);
        user.setImageResource(R.drawable.ic_profile_gray);

        search.setTextColor(Color.parseColor("#91959d"));
        offer.setTextColor(Color.parseColor("#91959d"));
        booking.setTextColor(Color.parseColor("#000000"));
        profile.setTextColor(Color.parseColor("#91959d"));
    }
}
