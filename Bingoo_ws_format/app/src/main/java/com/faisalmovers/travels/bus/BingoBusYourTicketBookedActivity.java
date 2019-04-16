package com.faisalmovers.travels.bus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BingoBusYourTicketBookedActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout male, female;
    TextView txtmale, txtfemale;
    TextView pickupfrom,topoint;
    TextView locpick,locdrop,totalamount;
    ImageView  back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingo_bus_your_ticket_booked);



        male = (LinearLayout) findViewById(R.id.male);
        female = (LinearLayout) findViewById(R.id.female);
        txtmale = (TextView) findViewById(R.id.txtmale);
        txtfemale = (TextView) findViewById(R.id.txtfemale);

        pickupfrom = (TextView) findViewById(R.id.from);
        topoint = (TextView) findViewById(R.id.to);

        locpick = (TextView) findViewById(R.id.pickup);
        locdrop = (TextView) findViewById(R.id.dropping);

        totalamount = (TextView) findViewById(R.id.total);


        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Bingobus24Activity.class);
                startActivity(i);
                finish();
            }
        });


        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
//
        pickupfrom.setText(pref.getString("from", null));
        topoint.setText(pref.getString("to", null));;
        locpick.setText(pref.getString("city", null));
        locdrop.setText(pref.getString("city1", null));
        totalamount.setText("Rs "+pref.getString("totalamountofseat", null));



        male.setBackgroundResource(R.drawable.rectangle_cure_blue1);
        txtmale.setTextColor(Color.parseColor("#009339"));

        male.setOnClickListener(this);
        female.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.male:
                male.setBackgroundResource(R.drawable.rectangle_cure_blue1green);
                female.setBackgroundResource(R.drawable.rectangle_cure_gray);
                txtmale.setTextColor(Color.parseColor("#ffffff"));
                txtfemale.setTextColor(Color.parseColor("#91959d"));
                break;

            case R.id.female:
                female.setBackgroundResource(R.drawable.rectangle_cure_blue1green);
                male.setBackgroundResource(R.drawable.rectangle_cure_gray);
                txtfemale.setTextColor(Color.parseColor("#ffffff"));
                txtmale.setTextColor(Color.parseColor("#91959d"));
                break;
        }
    }
}
