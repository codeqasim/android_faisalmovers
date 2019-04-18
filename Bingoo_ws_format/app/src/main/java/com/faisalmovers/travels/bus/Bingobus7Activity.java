package com.faisalmovers.travels.bus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.TimeZone;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

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
import java.util.Calendar;
import java.util.Date;

import Adapter.Bingobus7Adapter;
import model.Bingobus7Model;
import util.Url;
import util.Utils;

public class Bingobus7Activity extends Url {

    public RecyclerView recyclerView;
    public Bingobus7Adapter bingobas7_adapter;

    public ArrayList<Bingobus7Model> businfo;
    TextView up,down;
    ImageView back;
    Context context=this;
    Animation animation;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    String responmessage;
    Bingobus7Model bingobus7Model;
    ProgressBar progressBar2 ;
    String selectdate ;
    Date oneWayTripDate;
    TextView dateinfo;
    String datetime=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingobus7);

        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);


        businfo = new ArrayList<>();



        dateinfo = (TextView) findViewById(R.id.dateinfo);
        dateinfo.setText("loading");

        back = (ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i = new Intent(Bingobus7Activity.this,Bingobus24Activity.class);
                */
                finish();
                /*overridePendingTransition(R.anim.left_in, R.anim.right_out);
                startActivity(i);*/
            }
        });

        up = findViewById(R.id.up);
        down = findViewById(R.id.down);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        up.setText(pref.getString("from",null));
        down.setText(pref.getString("to",null));

        recyclerView = (RecyclerView)findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(new LinearLayoutManager(Bingobus7Activity.this));
        recyclerView.setFocusableInTouchMode(false);
        recyclerView.setNestedScrollingEnabled(false);



        String fromCityId = pref.getString("fromcityid",null);
        String toCityId =  pref.getString("tocityid",null);

        selectdate = pref.getString("selectdate",null);




        Log.d("datedate",selectdate);
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat d= new SimpleDateFormat("dd MMM yyyy");
        try {
            Date convertedDate = inputFormat.parse(selectdate);
            datetime = d.format(convertedDate);

            dateinfo.setText(businfo.size() +" Buses Found for "+datetime );
        }catch (ParseException e)
        {

        }

      Log.d("fromCityIdfromCityId" , selectdate);

        String businfo = businfoweb+fromCityId+"&toCityId="+toCityId+"&pdate="+selectdate+"&type=getSchedules";
        Log.d("fromCityIdfromCityId" , businfo);


        if (Utils.isNetworkAvailable(getApplicationContext())) {

            sendAndRequestResponse(businfo ,recyclerView);
        } else {
            Utils.showErrorToast(getApplicationContext(), "NETWORK CONNECTION");


        }
//        animation content slowly updated in view for using this code
//        runAnimation(recyclerView);


      /*  recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(context,
                recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        Intent i = new Intent(context, Bingobus33Activity.class);
                        context.startActivity(i);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );*/

    }
    private void runAnimation(RecyclerView recyclerview) {
        Context context = recyclerview.getContext();
        LayoutAnimationController controller = null;



      /* bingobas7_models.add(new Bingobus7Model("A/C Sleeper (2+1)"));
        bingobas7_models.add(new Bingobus7Model("A/C Sleeper (2+1)"));
        bingobas7_models.add(new Bingobus7Model("A/C Sleeper (2+1)"));
        bingobas7_models.add(new Bingobus7Model("A/C Sleeper (2+1)"));
        bingobas7_models.add(new Bingobus7Model("A/C Sleeper (2+1)"));
        bingobas7_models.add(new Bingobus7Model("A/C Sleeper (2+1)"));
        bingobas7_models.add(new Bingobus7Model("A/C Sleeper (2+1)"));*/

        controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fall_down);

      // bingobas7_adapter = new Bingobus7Adapter(Bingobus7Activity.this, bingobas7_models);
       //recyclerview.setAdapter(bingobas7_adapter);

        recyclerview.setLayoutAnimation(controller);
        recyclerview.getAdapter().notifyDataSetChanged();
        recyclerview.scheduleLayoutAnimation();


    }

    @Override
    public void onBackPressed()
    {
        // code here to show dialog
       // super.onBackPressed();
        finish();
        /*Intent i = new Intent(Bingobus7Activity.this,ListinwsActivity.class);
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
       startActivity(i);*/
    }

    private String  sendAndRequestResponse(String url , final RecyclerView recyclerview) {




        mRequestQueue = Volley.newRequestQueue(this);
        mStringRequest = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {


                Log.d("responseresponse" ,response);
                responmessage = response ;
                try {


                    JSONArray jsonArray = new JSONArray(response);

                    for (int i =0; i <jsonArray.length(); i++)
                    {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);


                        String  acsleeper = "A/C Sleeper (2+1)" ;
                        String departureTime = jsonObject.getString("departureTime");
                        departureTime =  timedate(departureTime);
                        String arrivalTime = jsonObject.getString("arrivalTime") ;
                        arrivalTime = timedate(arrivalTime);
                        String fare  = jsonObject.getString("fare");
                        String busType = jsonObject.getString("busType");
                        String stops  = jsonObject.getString("stops");
                        String status  = jsonObject.getString("status");
                        String Seats = jsonObject.getString("Seats") ;
                        String status1 = jsonObject.getString("status1");
                        String Schedule_Id = jsonObject.getString("Schedule_Id") ;
                        String MaskDate = jsonObject.getString("MaskDate") ;
                        String MaskRouteCode  = jsonObject.getString("MaskRouteCode");
                        String MaskTerminalId = jsonObject.getString("MaskTerminalId");
                        String ExcludedTerminalsList  = jsonObject.getString("ExcludedTerminalsList");
                        String cancellationpolicy = jsonObject.getString("cancellationpolicy") ;
                        String Amenities  = jsonObject.getString("Amenities");
                        String DropingPoints = jsonObject.getString("DropingPoints");
                        String QuerydepartureTime = jsonObject.getString("QuerydepartureTime") ;
                        QuerydepartureTime = timedate(QuerydepartureTime);

                        Bingobus7Model   bingobus7Model =  new Bingobus7Model(acsleeper,departureTime,arrivalTime,
                                fare,busType,stops,status,Seats,status1,Schedule_Id,MaskDate,MaskRouteCode,
                                MaskTerminalId,ExcludedTerminalsList,cancellationpolicy,Amenities,DropingPoints,QuerydepartureTime);
                        businfo.add(bingobus7Model);


                    }


                    Context context = recyclerview.getContext();
                    LayoutAnimationController controller = null;
                    progressBar2.setVisibility(View.GONE);
                    controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fall_down);
                    bingobas7_adapter = new Bingobus7Adapter(Bingobus7Activity.this,businfo );
                    recyclerview.setAdapter(bingobas7_adapter);

                    recyclerview.setLayoutAnimation(controller);
                    recyclerview.getAdapter().notifyDataSetChanged();

                    recyclerview.scheduleLayoutAnimation();

                    if (bingobas7_adapter.getItemCount() <= 0)
                    {
                        progressBar2.setVisibility(View.GONE);
                        Utils.showInfoToast(getApplicationContext() ,"no data avaible for this way");
                    }




                    dateinfo.setText(businfo.size() +" Buses Found for "+datetime );

                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                progressBar2.setVisibility(View.GONE);
                Utils.showInfoToast(getApplicationContext() ,"no data avaible for this way");
                dateinfo.setText(businfo.size() +" Buses Found for "+datetime );
                responmessage = String.valueOf(error);
            }
        });
        mRequestQueue.add(mStringRequest);

        return responmessage ;
    }


    public String timedate (String time )
    {


        try {
            String _24HourTime = time;
            SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
            SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
            Date _24HourDt = _24HourSDF.parse(_24HourTime);
            System.out.println(_24HourDt);
            time = _12HourSDF.format(_24HourDt).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return time;
    }
}