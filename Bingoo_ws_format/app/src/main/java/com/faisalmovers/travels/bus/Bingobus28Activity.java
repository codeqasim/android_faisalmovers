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

import model.Bingobus7Model;

public class Bingobus28Activity extends AppCompatActivity {

    ImageView cancel;
    TextView from ,to ,price,operator,seatcountt,total,busType;
    Context context =this;
    Bingobus7Model bingobus7Model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingobus28);
        Intent da = getIntent();
        bingobus7Model = (Bingobus7Model)da.getSerializableExtra("sampleObject");
        String operator1 = bingobus7Model.getOperator();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
//

        from = (TextView) findViewById(R.id.from);
        to = (TextView) findViewById(R.id.to);
        from.setText(pref.getString("from", null));
        price= (TextView) findViewById(R.id.price);
        total= (TextView) findViewById(R.id.total);
        to.setText(pref.getString("to", null));
        cancel = (ImageView) findViewById(R.id.cancel);
        operator= (TextView) findViewById(R.id.operator);
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
            seatcountt.setText(pref.getString("seatcount", null));
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