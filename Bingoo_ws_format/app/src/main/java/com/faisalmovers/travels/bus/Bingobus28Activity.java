package com.faisalmovers.travels.bus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import model.Bingobus7Model;
import model.Seat;
import model.seatModel;

public class Bingobus28Activity extends AppCompatActivity {


    ArrayList<String> map =new ArrayList<>();
    Gson gson = new Gson();;
    ImageView cancel;
    TextView from ,to ,price,operator,seatcountt,total,busType,Seatsinfo;
    Context context =this;
    Bingobus7Model bingobus7Model;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingobus28);
        Intent da = getIntent();
        bingobus7Model = (Bingobus7Model)da.getSerializableExtra("sampleObject");
        String operator1 = bingobus7Model.getDepartureTime();

         pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
         editor = pref.edit();

        from = (TextView) findViewById(R.id.from);
        to = (TextView) findViewById(R.id.to);
        from.setText(pref.getString("from", null));
        price= (TextView) findViewById(R.id.price);
        total= (TextView) findViewById(R.id.total);
        to.setText(pref.getString("to", null));
        cancel = (ImageView) findViewById(R.id.cancel);
        operator= (TextView) findViewById(R.id.operator);
        Seatsinfo= (TextView) findViewById(R.id.Seatsinfo);

        operator.setText(operator1);
        price.setText(bingobus7Model.getPrice());

        seatcountt = (TextView) findViewById(R.id.seatcountt);
        busType = (TextView) findViewById(R.id.busType);

        busType.setText(bingobus7Model.getBusType());

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });


        if(pref.getString("seatcount", null) ==null)
        {
            seatcountt.setText("0");
        }else {



            String numberofseat = pref.getString("numberofseat", " ");
            seatcountt.setText(pref.getString("seatcount", " ") );
            Seatsinfo.setText(numberofseat);
        }





        String numberseat = pref.getString("seatcount", null);
        String pric = bingobus7Model.getPrice();


        int numberseat1;
        int pric1;
        try {
            numberseat1 = Integer.parseInt(numberseat);
            pric1 = Integer.parseInt(pric);

            int to = numberseat1*pric1;
            total.setText("Rs :" +""+to);
        }
        catch (NumberFormatException e)
        {
            numberseat1 = 0;
            total.setText(bingobus7Model.getPrice());
        }






    }







}