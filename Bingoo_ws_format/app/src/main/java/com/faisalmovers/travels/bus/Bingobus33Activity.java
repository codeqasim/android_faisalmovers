package com.faisalmovers.travels.bus;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import java.util.ArrayList;
import java.util.List;

import Adapter.AirplaneAdapter;
import model.AbstractItem;
import model.Bingobus7Model;
import model.SeatData;
import util.Url;
import util.Utils;

public class Bingobus33Activity extends Url implements OnSeatSelected{

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
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    ProgressBar progressBar2;
    RecyclerView recyclerView;
    List<AbstractItem> items = new ArrayList<>();
    List<SeatData> Seatdata = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingobus33);



         da = getIntent();
        bingobus7Model = (Bingobus7Model)da.getSerializableExtra("sampleObject");
        price = bingobus7Model.getPrice();
        pricetickets = Integer.parseInt(price);

        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);




        String requestinfoseat=seatinfo;
        Log.d("fromCityIdfromCityId" , requestinfoseat);


        txtSeatSelected = (TextView)findViewById(R.id.txt_seat_selected);
        seatnum = (TextView)findViewById(R.id.seatnum);
        seatnum.setText( "Seats Selected : "+String.valueOf(seatcount));

        back_press = (ImageView) findViewById(R.id.back_press);
        back_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });



        recipt = (LinearLayout)findViewById(R.id.recipt);
        recipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, Bingobus28Activity.class);
                i.putExtra("sampleObject", bingobus7Model);
                context.startActivity(i);

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
                SharedPreferences pref = context.getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("totalamountofseat", totalamountofseat);
                editor.putString("numberofseat", numberofseat);
                editor.putString("seatcount", String.valueOf(seatcount));
                editor.commit();
                Intent i = new Intent(context, Bingobus31Activity.class);
                i.putExtra("sampleObject", bingobus7Model);
                context.startActivity(i);

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

        GridLayoutManager manager = new GridLayoutManager(this, COLUMNS);
        recyclerView = (RecyclerView) findViewById(R.id.lst_items);
        recyclerView.setLayoutManager(manager);

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
    public void onSeatSelected(int x) {
        //txtSeatSelected.setText("Book "+count+" seats");
        if (x==2){
            count+=pricetickets;
            seatcount+=1;
            txtSeatSelected.setText(String.valueOf(count));
            seatnum.setText("Seats Selected : "+String.valueOf(seatcount));

            SharedPreferences pref = context.getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("seatcount", String.valueOf(seatcount));
            editor.commit();
        }
        else if (x==1){
            count-=pricetickets;
            seatcount-=1;
            txtSeatSelected.setText(String.valueOf(count));
            seatnum.setText( "Seats Selected : "+String.valueOf(seatcount));
            SharedPreferences pref = context.getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("seatcount", String.valueOf(seatcount));
            editor.commit();
        }
        else {

        }
    }

    @Override
    public void onBackPressed()
    {
       /* Intent i = new Intent(Bingobus33Activity.this, ListinwsActivity.class);
        finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
        startActivity(i);*/
        // code here to show dialog
        super.onBackPressed();

        finish();
        // optional depending on your needs
    }




    @Override
    protected void onPause() {
        super.onPause();

        bingobus7Model = (Bingobus7Model)da.getSerializableExtra("sampleObject");
        price = bingobus7Model.getPrice();
        pricetickets = Integer.parseInt(price);
        Log.d("lifecycle3","onPause invoked" +price);
    }

    private void  sendAndRequestResponse2(String url, final Context context ) {




        mRequestQueue = Volley.newRequestQueue(this);
        mStringRequest = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(String response) {

                Log.d("requestinfoseat",response);
                try {



                    JSONObject jsonObject1 = new JSONObject(response);
                    JSONObject response1 = jsonObject1.getJSONObject("response");
                    JSONArray data = response1.getJSONArray("data");
                    Log.d("requestinfoseat",data.length()+"///");

                    for(int i =0; i<data.length();i++)
                    {

                        SeatData seatData = new SeatData();
                        JSONObject jsonObject = data.getJSONObject(i);
                        String seat_id = jsonObject.getString("seat_id");
                        String seat_name = jsonObject.getString("seat_name");
                        String seat_type= jsonObject.getString("seat_type");
                        String seat_status= jsonObject.getString("seat_status");
                        String row_name= jsonObject.getString("row_name");
                        String col_index= jsonObject.getString("col_index");
                        String row_size= jsonObject.getString("row_size");
                        String isAvailableForBooking= jsonObject.getString("isAvailableForBooking");
                        String AreaCategoryCod= jsonObject.getString("AreaCategoryCod");
                        String Seat_No= jsonObject.getString("Seat_No");
                        String fare = jsonObject.getString("fare");
                        String Gender= jsonObject.getString("Gender");

                        seatData.setSeat_id(seat_id);
                        seatData.setSeat_name(seat_name);
                        seatData.setSeat_type(seat_type);
                        seatData.setSeat_status(seat_status);
                        seatData.setRow_name(row_name);
                        seatData.setCol_index(col_index);
                        seatData.setRow_size(row_size);
                        seatData.setIsAvailableForBooking(isAvailableForBooking);
                        seatData.setAreaCategoryCod(AreaCategoryCod);
                        seatData.setSeat_No(Seat_No);
                        seatData.setFare(fare);
                        seatData.setGender(Gender);
                        Seatdata.add(seatData);


                       }
                    progressBar2.setVisibility(View.GONE);

                    for (int i=0; i<data.length(); i++) {

                        if (i%COLUMNS==0 || i%COLUMNS==4)
                        {

                            items.add(new EdgeItem(String.valueOf(i)));
                        } else if (i%COLUMNS==1 || i%COLUMNS==3)
                        {
                            items.add(new CenterItem(String.valueOf(i)));
                        } else {
                            items.add(new EmptyItem(String.valueOf(i)));
                        }
                    }
                    AirplaneAdapter adapter = new AirplaneAdapter(context, items);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }




            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {



            }
        });
        mRequestQueue.add(mStringRequest);
    }

}