package com.faisalmovers.travels.bus;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapter.AirplaneAdapter;
import model.AbstractItem;
import model.Bingobus7Model;
import model.Seat;
import model.SeatData;
import model.seatModel;
import util.Url;
import util.Utils;

public class Bingobus33Activity extends Url implements OnSeatSelected{

    ArrayList<seatModel> map =new ArrayList<>();
    ArrayList<String> seat_ids =new ArrayList<>();
    private static final int COLUMNS = 5;
    private TextView txtSeatSelected,seatnum;
    int count=0,seatcount=0;
    LinearLayout next;
    LinearLayout detailes,recipt;
    Dialog slideDialog;
    ImageView back_press;
    ImageView cancel;
    private AlertDialog.Builder builder;
    Context context =this;
    Bingobus7Model bingobus7Model;
    int pricetickets =0;
    String price ="0";
    Intent da;
    LinearLayout mainbusdesing;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    ProgressBar progressBar2;
    RecyclerView recyclerView;
    List<AbstractItem> items = new ArrayList<>();
    List<SeatData> Seatdata = new ArrayList<>();
    List<Seat> bookseats = new ArrayList<>();
    ArrayList<AbstractItem> list_models=new ArrayList<>();
    Gson gson ;
    public static final String PREFS_NAME = "MyApp_Settings";
    SharedPreferences settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingobus33);



        da = getIntent();
        bingobus7Model = (Bingobus7Model)da.getSerializableExtra("sampleObject");
        price = bingobus7Model.getPrice();
        pricetickets = Integer.parseInt(price);

        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        mainbusdesing = findViewById(R.id.mainbusdesing);
         gson = new Gson();
        String scheduleid =bingobus7Model.getScheduleID() ;
        String departureTime =bingobus7Model.getDepartureTime2();
        String querydepartureTime =bingobus7Model.getQuerydepartureTime2();



        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        String fromCityId = pref.getString("fromcityid",null);
        String toCityId =  pref.getString("tocityid",null);
        final String selectdate =pref.getString("selectdate",null);
       /* SharedPreferences.Editor editor = pref.edit();
        editor.putString("seatcount", "0");
        editor.commit();*/


        String webseat = seatinfo+bingobus7Model.getOpId()+"&scheduleid="+scheduleid+"&depdate="+selectdate+"&fromcity="+fromCityId+"&tocity="+toCityId+"&deptime="+departureTime+"&depqtime="+querydepartureTime;
        String requestinfoseat=webseat;
        //Log.d("fromCityIdfromCityId" , departureTime+"/  "+webseat);
        Log.d("fromCityIdfromCityId" , webseat);

        txtSeatSelected = (TextView)findViewById(R.id.txt_seat_selected);
        seatnum = (TextView)findViewById(R.id.seatnum);
        seatnum.setText( "Seats Selected : "+String.valueOf(seatcount));

        back_press = (ImageView) findViewById(R.id.back_press);
        back_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Log.d("fromCityIdfromCityId", seatcount + "/");


                if (seatcount >= 1)
                {
                    dailogbox();
                }else
                {
                    finish();
                }




        //        finish();

            }
        });



        recipt = (LinearLayout)findViewById(R.id.recipt);
        recipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String numberofseat = seatnum.getText().toString();
                SharedPreferences pref = context.getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
               // editor.putString("totalamountofseat", totalamountofseat);
                editor.putString("numberofseat", numberofseat);
                //editor.putString("seatcount", String.valueOf(seatcount));
               // editor.putString("seatlist", seatlist);
                editor.commit();


                Intent i = new Intent(context, Bingobus28Activity.class);
                i.putExtra("sampleObject", bingobus7Model);
                startActivity(i);
                finish();

            }
        });




        detailes = (LinearLayout)findViewById(R.id.detailes);
        detailes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, Bingobus27Activity.class);
                i.putExtra("sampleObject", bingobus7Model);
                context.startActivity(i);

            }
        });

        next = (LinearLayout)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String totalamountofseat = txtSeatSelected.getText().toString();
                String numberofseat = seatnum.getText().toString();

                Log.d("mappsize" ,map.size()+" ");
                addarray();
               if(map.size()>0)
               {


                   String seatlist = gson.toJson(bookseats);
                   SharedPreferences pref = context.getSharedPreferences("MyPref", MODE_PRIVATE);
                   SharedPreferences.Editor editor = pref.edit();
                   editor.putString("totalamountofseat", totalamountofseat);
                   editor.putString("numberofseat", numberofseat);
                   editor.putString("seatcount", String.valueOf(seatcount));
                   editor.putString("seatlist", seatlist);
                   editor.commit();
                   Intent i = new Intent(context, Bingobus31Activity.class);
                   i.putExtra("sampleObject", bingobus7Model);
                   context.startActivity(i);
               }else
               {
                   Utils.showErrorToast(getApplicationContext()," sealect seat ");
               }

            }
        });



       /* for (int i=0; i<50; i++) {

            if (i%COLUMNS==0 || i%COLUMNS==4)
            {

                items.add(new EdgeItem(String.valueOf(i)));
            } else if (i%COLUMNS==1 || i%COLUMNS==3)
            {
                items.add(new CenterItem(String.valueOf(i)));
            } else {
                items.add(new EmptyItem(String.valueOf(i)));
            }
        }*/

       // GridLayoutManager manager = new GridLayoutManager(this, COLUMNS);
        recyclerView = (RecyclerView) findViewById(R.id.lst_items);
        //recyclerView.setLayoutManager(manager);

       /* AirplaneAdapter adapter = new AirplaneAdapter(this, items);
        recyclerView.setAdapter(adapter);*/

        if (Utils.isNetworkAvailable(getApplicationContext())) {
            sendAndRequestResponse2(requestinfoseat,this);
        } else {
            Utils.showErrorToast(getApplicationContext(), "NETWORK CONNECTION");

            progressBar2.setVisibility(View.GONE);
        }






    }


    @Override
    public void onSeatSelected(seatModel modelSeat) {

        if( map.contains(modelSeat))
        {
            map.remove(modelSeat);

            seatcount =seatcount-1;
            unholdseat(seatUnHold,modelSeat.getSeat_id());

        }else{
            map.add(modelSeat);

            seatcount =seatcount+1;

           /* SharedPreferences  pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
            String origin =pref.getString("fromcityid", null);
            String destination =pref.getString("tocityid", null);
            seathold(seatHold,modelSeat.getSeat_id(),origin,destination);*/
        }
        seatnum.setText("");
        int totalPrice=0;
        if(map.size()>0){

            for(int i=0;i<map.size();i++)
            {
                if(i==0)

                    seatnum.append(map.get(i).getSeat_on());
                else
                    seatnum.append(","+map.get(i).getSeat_on());

                if(!map.get(i).getSeat_fare().equals("null"))
                {

                    totalPrice = totalPrice+Integer.parseInt(map.get(i).getSeat_fare());


                }
                txtSeatSelected.setText(totalPrice+"");
            }

        }else{
            seatnum.setText("0");
            txtSeatSelected.setText("0");
        }



        Log.d("seatcountcccc;",  seatcount+" ");
        SharedPreferences pref = context.getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("seatcount", String.valueOf(seatcount));
        editor.putString("seatcountdatanumber", String.valueOf(seatcount));
        editor.commit();
        addarray();
    }


    public void seathold(final String url, final String seat_id , final String origin, final String destination)
    {
        progressBar2.setVisibility(View.VISIBLE);
        mRequestQueue = Volley.newRequestQueue(this);
        mStringRequest  = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar2.setVisibility(View.GONE);

                Log.d("seatidtest", "hold /"+response);


                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String  status = jsonObject.getString("Status");
                   // Log.d("seatholdseathold", " "+status);


                    if (status.contentEquals("success"))
                    {
                       // Utils.showInfoToast(context,"Your seat is hold");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("seatholdseathold","error/" +error);
                Utils.showErrorToast(getApplicationContext(),"server issue");
                progressBar2.setVisibility(View.GONE);
                //This code is executed if there is an error.
                //inProgress=false;
            }
        }) {
            @Override
            protected Map<String, String> getParams()  {



                Map<String, String> params = new HashMap<String, String>();
                params.put("seat_id",seat_id);
                params.put("origin",origin);
                params.put("destination",destination);
                String json  =gson.toJson(params) ;
                Log.d("seatholdseathold",json);
                return params;


            }
        };

        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(mStringRequest);
    }



    public void unholdseat(final String url, final String seat_id )
    {
        progressBar2.setVisibility(View.VISIBLE);
        mRequestQueue = Volley.newRequestQueue(this);
        mStringRequest  = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressBar2.setVisibility(View.GONE);

                Log.d("seatholdseathold", "unhold"+"/n"+response);


                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String  status = jsonObject.getString("Status");
                  //  Log.d("seatholdseathold", " "+status);


                    if (status.contentEquals("success"))
                    {
                      //  Utils.showErrorToast(context,"Your seat is unhold");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("seatholdseathold","error/" +error);
                Utils.showErrorToast(getApplicationContext(),"server issue");
                progressBar2.setVisibility(View.GONE);
                //This code is executed if there is an error.
                //inProgress=false;
            }
        }) {
            @Override
            protected Map<String, String> getParams()  {



                Map<String, String> params = new HashMap<String, String>();
                params.put("seat_id",seat_id);
                String json  =gson.toJson(params) ;
            //    Log.d("seatholdseathold",json);
                return params;


            }
        };

        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(mStringRequest);
    }



    @Override
    public void onBackPressed()
    {
        if (seatcount >= 1)
        {
            dailogbox();
        }else
        {
            finish();
        }


        // optional depending on your needs
    }




    @Override
    protected void onPause() {
        super.onPause();

       /* bingobus7Model = (Bingobus7Model)da.getSerializableExtra("sampleObject");
        price = bingobus7Model.getPrice();
        pricetickets = Integer.parseInt(price);*/
        Log.d("lifecycle3","onPause invoked" +price);
    }

    private void  sendAndRequestResponse2(final String url, final Context context ) {




        mRequestQueue = Volley.newRequestQueue(this);
        mStringRequest = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override

            public void onResponse(String response) {


            Log.d("responceschek", url);
                progressBar2.setVisibility(View.GONE);
                mainbusdesing.setVisibility(View.VISIBLE);
                JSONObject parentObject = null;
                try {
                    parentObject = new JSONObject(response);

                    JSONObject mainObject=parentObject.getJSONObject("response");
                    JSONArray mainArray=mainObject.getJSONArray("data");
                    seatModel sModel;

                    if(mainArray.length() <= 0)
                    {
                        finish();
                        Toast.makeText(getBaseContext(),"Try Agian Server Error",Toast.LENGTH_LONG).show();
                    }else{

                        if(bingobus7Model.getBusType().equals("Business Class"))
                        {
                            for(int i=0;i<30;i++)
                            {

                                JSONObject jsonObject=mainArray.getJSONObject(i);
                                sModel=new seatModel();

                                sModel.setSeat_fare(jsonObject.getString("fare"));
                                sModel.setSeat_on(jsonObject.getString("Seat_No"));
                                sModel.setSeat_id(jsonObject.getString("seat_id"));
                                sModel.setSeat_status(jsonObject.getString("seat_status"));
                                sModel.setGender(jsonObject.getString("Gender"));


                                list_models.add(new EdgeItem(sModel));

                            }
                            list_models.add(2,new EmptyItem(new seatModel()));
                            list_models.add(6,new EmptyItem(new seatModel()));
                            list_models.add(10,new EmptyItem(new seatModel()));
                            list_models.add(14,new EmptyItem(new seatModel()));
                            list_models.add(18,new EmptyItem(new seatModel()));
                            list_models.add(22,new EmptyItem(new seatModel()));
                            list_models.add(26,new EmptyItem(new seatModel()));
                            list_models.add(30,new EmptyItem(new seatModel()));
                            list_models.add(34,new EmptyItem(new seatModel()));
                            list_models.add(38,new EmptyItem(new seatModel()));





                            GridLayoutManager  mLayoutManager = new GridLayoutManager(context, 4);
                            final AirplaneAdapter mAdapter = new AirplaneAdapter(context,list_models,progressBar2);

                         /*   mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                @Override
                                public int getSpanSize(int position) {
                                    switch(mAdapter.getItemViewType(position)){
                                        case AbstractItem.TYPE_FOOTER:
                                            return 4;

                                        default:
                                            return 1;
                                    }
                                }
                            });
                            recyclerView.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                            recyclerView.addItemDecoration(new RecyclerViewItemDecorator(context));
                            recyclerView.setHasFixedSize(true);
//                            recyclerView.setPaddingRelative(55,0,0,0);*/
                            recyclerView.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setAdapter(mAdapter);

                        }else if(bingobus7Model.getBusType().equals("Normal") || bingobus7Model.getBusType().equals("Standard")){


                            for(int i=0;i<mainArray.length();i++)
                            {

                                JSONObject jsonObject=mainArray.getJSONObject(i);
                                sModel=new seatModel();

                                sModel.setSeat_fare(jsonObject.getString("fare"));
                                sModel.setSeat_on(jsonObject.getString("Seat_No"));
                                sModel.setSeat_id(jsonObject.getString("seat_id"));
                                sModel.setSeat_status(jsonObject.getString("seat_status"));
                                sModel.setGender(jsonObject.getString("Gender"));

                                list_models.add(new EdgeItem(sModel));

                            }

                            list_models.add(2,new EmptyItem(new seatModel()));
                            list_models.add(7,new EmptyItem(new seatModel()));
                            list_models.add(12,new EmptyItem(new seatModel()));
                            list_models.add(17,new EmptyItem(new seatModel()));
                            list_models.add(22,new EmptyItem(new seatModel()));
                            list_models.add(27,new EmptyItem(new seatModel()));
                            list_models.add(32,new EmptyItem(new seatModel()));
                            list_models.add(37,new EmptyItem(new seatModel()));
                            list_models.add(42,new EmptyItem(new seatModel()));
                            list_models.add(47,new EmptyItem(new seatModel()));




                            GridLayoutManager  mLayoutManager = new GridLayoutManager(context, 5);
                            final AirplaneAdapter mAdapter = new AirplaneAdapter(context,list_models,progressBar2);
/*
                            mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                @Override
                                public int getSpanSize(int position) {
                                    switch(mAdapter.getItemViewType(position)){
                                        case AbstractItem.TYPE_FOOTER:
                                            return 5;

                                        default:
                                            return 1;
                                    }
                                }
                            });


                            recyclerView.addItemDecoration(new RecyclerViewItemDecorator(context));
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setPaddingRelative(55,0,0,0);*/
                            recyclerView.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setAdapter(mAdapter);

                        }else if(bingobus7Model.getBusType().equals("Executive")){


                            for(int i=0;i<mainArray.length()-5;i++)
                            {

                                JSONObject jsonObject=mainArray.getJSONObject(i);
                                sModel=new seatModel();

                                sModel.setSeat_fare(jsonObject.getString("fare"));
                                sModel.setSeat_on(jsonObject.getString("Seat_No"));
                                sModel.setSeat_id(jsonObject.getString("seat_id"));
                                sModel.setSeat_status(jsonObject.getString("seat_status"));
                                sModel.setGender(jsonObject.getString("Gender"));

                                list_models.add(new EdgeItem(sModel));

                            }

                            list_models.add(2,new EmptyItem(new seatModel()));
                            list_models.add(7,new EmptyItem(new seatModel()));
                            list_models.add(12,new EmptyItem(new seatModel()));
                            list_models.add(17,new EmptyItem(new seatModel()));
                            list_models.add(22,new EmptyItem(new seatModel()));
                            list_models.add(27,new EmptyItem(new seatModel()));
                            list_models.add(32,new EmptyItem(new seatModel()));
                            list_models.add(37,new EmptyItem(new seatModel()));
                            list_models.add(42,new EmptyItem(new seatModel()));
                            list_models.add(47,new EmptyItem(new seatModel()));
                           // list_models.add(52,new EmptyItem(new seatModel()));




                            GridLayoutManager  mLayoutManager = new GridLayoutManager(context, 5);
                            final AirplaneAdapter mAdapter = new AirplaneAdapter(context,list_models,progressBar2);

                          /*  mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                @Override
                                public int getSpanSize(int position) {
                                    switch(mAdapter.getItemViewType(position)){
                                        case AbstractItem.TYPE_FOOTER:
                                            return 5;

                                        default:
                                            return 1;
                                    }
                                }
                            });*/


                           // recyclerView.addItemDecoration(new RecyclerViewItemDecorator(context));
                            //recyclerView.setHasFixedSize(true);
                            //recyclerView.setPaddingRelative(55,0,0,0);
                            recyclerView.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setAdapter(mAdapter);

                        }else if(bingobus7Model.getBusType().equals("Executive Plus")){



                            for(int i=0;i<mainArray.length()-8;i++)
                            {

                                JSONObject jsonObject=mainArray.getJSONObject(i);
                                sModel=new seatModel();

                                sModel.setSeat_fare(jsonObject.getString("fare"));
                                sModel.setSeat_on(jsonObject.getString("Seat_No"));
                                sModel.setSeat_id(jsonObject.getString("seat_id"));
                                sModel.setSeat_status(jsonObject.getString("seat_status"));
                                sModel.setGender(jsonObject.getString("Gender"));

                                list_models.add(new EdgeItem(sModel));

                            }

                            list_models.add(2,new EmptyItem(new seatModel()));
                            list_models.add(3,new EmptyItem(new seatModel()));
                            list_models.add(7,new EmptyItem(new seatModel()));
                            list_models.add(8,new EmptyItem(new seatModel()));
                            list_models.add(12,new EmptyItem(new seatModel()));
                            list_models.add(13,new EmptyItem(new seatModel()));
                            list_models.add(17,new EmptyItem(new seatModel()));
                            list_models.add(22,new EmptyItem(new seatModel()));
                            list_models.add(27,new EmptyItem(new seatModel()));
                            list_models.add(32,new EmptyItem(new seatModel()));
                            list_models.add(37,new EmptyItem(new seatModel()));
                            list_models.add(42,new EmptyItem(new seatModel()));
                            list_models.add(47,new EmptyItem(new seatModel()));
//                            list_models.add(52,new EmptyItem(new seatModel()));




                            GridLayoutManager  mLayoutManager = new GridLayoutManager(context, 5);
                            final AirplaneAdapter mAdapter = new AirplaneAdapter(context,list_models,progressBar2);

                           /* mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                @Override
                                public int getSpanSize(int position) {
                                    switch(mAdapter.getItemViewType(position)){
                                        case AbstractItem.TYPE_FOOTER:
                                            return 5;

                                        default:
                                            return 1;
                                    }
                                }
                            });


                            recyclerView.addItemDecoration(new RecyclerViewItemDecorator(context));
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setPaddingRelative(55,0,0,0);*/
                            recyclerView.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setAdapter(mAdapter);

                        }

                    }





                }
                catch(JSONException e){
                    e.printStackTrace();
                    Log.d("Errors",e.getMessage());
                }
            }

        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressBar2.setVisibility(View.GONE);
                Utils.showErrorToast(getApplicationContext(),"Server error");


            }
        });
        mRequestQueue.add(mStringRequest);
    }


    public void addarray()
    {
        bookseats.clear();
        seat_ids.clear();
        String seatlist = gson.toJson(map);
        Log.d("adddarraydata",seatlist);

        try {
            JSONArray jsonObject1 = new JSONArray(seatlist);
            for (int i =0; i<jsonObject1.length(); i++)
            {
                JSONObject jsonObject = jsonObject1.getJSONObject(i);
                String  seat_fare = jsonObject.getString("seat_fare");
                String  seat_id = jsonObject.getString("seat_id");
                String  seat_on = jsonObject.getString("seat_on");
                String  seat_status = jsonObject.getString("seat_status");

                seat_ids.add(seat_id);
                String id =  seat_id;
                String no1 =  seat_on ;
                String price =seat_fare;
                Seat seat1 = new Seat(id+"",no1+"",price);
                bookseats.add(seat1);
                String json = gson.toJson(bookseats);
                Log.d("jsonarray",json);





            }


            String seatlistn = gson.toJson(bookseats);
            SharedPreferences pref = context.getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("seatlist", seatlistn);
            editor.commit();


        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

    public  void dailogbox ()
    {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
        alertDialogBuilder.setMessage("Are you sure you want to exit?");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        String allIds = TextUtils.join(",", seat_ids);


                        Log.d("seatholdseathold",allIds);
                        allunholdseat1(multiSeatUnHold,allIds);

                    }
                });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                //  DialogInterface.dismiss();

                dialog.cancel();
            }
        });


        alertDialogBuilder.show();

        // alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(R.color.fm);
        //alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(R.color.fm);



    }



    public void allunholdseat1(final String url, final String seat_id )
    {
        progressBar2.setVisibility(View.VISIBLE);
        mRequestQueue = Volley.newRequestQueue(this);
        mStringRequest  = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressBar2.setVisibility(View.GONE);

                Log.d("seatholdseathold11", seat_id+"/n"+response);
                try {
                    JSONObject jsonObject1 = new JSONObject(response);

                    JSONArray jsonArray =jsonObject1.getJSONArray("data");

                    for (int i =0; i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String Status = jsonObject.getString("Status");
                        if (Status.contentEquals("success"))
                        {
                           // Utils.showErrorToast(getApplicationContext(),"All seats  Unhold");
                            finish();
                        }
                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("seatholdseathold","error/" +error);
                Utils.showErrorToast(getApplicationContext(),"server issue");
                progressBar2.setVisibility(View.GONE);
                //This code is executed if there is an error.
                //inProgress=false;
            }
        }) {
            @Override
            protected Map<String, String> getParams()  {

                Map<String, String> params = new HashMap<String, String>();
                params.put("seat_ids",seat_id);
                String json  =gson.toJson(params) ;
                //Log.d("seatholdseathold",json);
                return params;


            }
        };

        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(mStringRequest);
    }

}