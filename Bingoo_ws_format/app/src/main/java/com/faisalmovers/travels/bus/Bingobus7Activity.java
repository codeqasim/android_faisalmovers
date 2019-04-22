package com.faisalmovers.travels.bus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import java.util.Date;
import java.util.Iterator;

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
    String tour_type_arr[];

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






         Log.d("fromCityIdfromCityId" , selectdate);

       String newweb = businfoweb+fromCityId+"&tocity="+toCityId+"&depdate="+selectdate+"&operator=1";
       // String businfo = businfoweb+fromCityId+"&toCityId="+toCityId+"&pdate="+selectdate+"&type=getSchedules";


        Log.d("fromCityIdfromCityId" , newweb);


        if (Utils.isNetworkAvailable(getApplicationContext())) {

          // sendAndRequestResponse(businfo ,recyclerView);

           sendAndRequestResponse2(newweb,recyclerView);
        } else {
            Utils.showErrorToast(getApplicationContext(), "NETWORK CONNECTION");

            progressBar2.setVisibility(View.GONE);
        }
//        animation content slowly updated in view for using this code
//        runAnimation(recyclerView);


  recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(context,
                recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        bingobus7Model = businfo.get(position);
                        Intent i = new Intent(context,Bingobus33Activity.class);
                        i.putExtra("sampleObject", bingobus7Model);
                        startActivity(i);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

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


    private String  sendAndRequestResponse2(String url , final RecyclerView recyclerview) {




        mRequestQueue = Volley.newRequestQueue(this);
        mStringRequest = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {




                try {


                    JSONObject jsonObject1 = new JSONObject(response);
                    JSONObject response1 = jsonObject1.getJSONObject("response");
                    JSONObject data = response1.getJSONObject("data");
                    Log.d("data1data1",data.length()+"/");
                    Iterator keys = data.keys();


                    while(keys.hasNext()) {


                        String currentDynamicKey = (String) keys.next();

                        JSONArray mainArray = data.optJSONArray(currentDynamicKey);

                        Log.d("arraydata" ,mainArray.length()+"/");
                        if (mainArray != null) {

                            for(int i=0;i<mainArray.length();i++) {

                                ArrayList<String> amenitiesdata = new ArrayList<>();
                                ArrayList<String> amenitiesdataimage = new ArrayList<>();
                                ArrayList<String > boardingPoints = new ArrayList<>();
                                JSONObject jsonObject = mainArray.getJSONObject(i);

                               Bingobus7Model busModel = new Bingobus7Model();
                                busModel.setOperator(jsonObject.getString("operator"));
                                busModel.setLogo(jsonObject.getString("logo"));
                                busModel.setFromCity(jsonObject.getString("fromCity"));
                                busModel.setToCity(jsonObject.getString("toCity"));


                                busModel.setPrice(jsonObject.getString("price"));
                                busModel.setScheduleID(jsonObject.getString("scheduleID"));
                                busModel.setStops(jsonObject.getString("stops"));
                                busModel.setBusType(jsonObject.getString("busType"));
                                busModel.setOpId(jsonObject.getString("opId"));
                                busModel.setSeatsLeft(jsonObject.getString("seatsLeft"));


                                String QuerydepartureTime = jsonObject.getString("querydepartureTime") ;
                                QuerydepartureTime = timedate(QuerydepartureTime);
                                busModel.setQuerydepartureTime(QuerydepartureTime);

                                String arrivalTime = jsonObject.getString("arrivalTime");
                                arrivalTime = timedate(arrivalTime);
                                busModel.setArrivalTime(arrivalTime);

                                String departureTime = jsonObject.getString("departureTime");
                                departureTime = timedate(departureTime);
                                busModel.setDepartureTime(departureTime);


                                JSONArray amenities_listing = jsonObject.getJSONArray("amenities");
                                for (int amenities = 0 ; amenities<amenities_listing.length();amenities++){

                                    JSONObject amenitiesjson = amenities_listing.getJSONObject(amenities);
                                    String label = amenitiesjson.getString("label");
                                    String imagepth = amenitiesjson.getString("path");
                                    amenitiesdataimage.add(imagepth);
                                    amenitiesdata.add(label);
                                }

                                JSONArray boardingPointsjson = jsonObject.getJSONArray("boardingPoints");

                                for (int boardingPoint = 0 ; boardingPoint<boardingPointsjson.length();boardingPoint++){

                                    JSONObject amenitiesjson = boardingPointsjson.getJSONObject(boardingPoint);
                                    String terminal_name = amenitiesjson.getString("terminal_name");

                                    boardingPoints.add(terminal_name);
                                }




                                busModel.setAmenities_array_list(amenitiesdata);
                                busModel.setAmenities_array_listimage(amenitiesdataimage);
                                busModel.setBoardingPoints(boardingPoints);
                                businfo.add(busModel);

                            }

                        }


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
                   // dateinfo.setText(businfo.size() +" Buses Found for "+datetime );

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





}