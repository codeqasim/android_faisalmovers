package com.fm.travels.bus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Bingobus_Last_StepActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout male,female,bycash,easypisa;
    TextView txtmale,txtfemale;
    ImageView img1,img2,img1easypasa,img2easypasa,back;
    TextView searchbus;
    Context context=this;
    TextView pickupfrom,topoint;
    TextView locpick,locdrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingobus_last_step);

        bycash = (LinearLayout)findViewById(R.id.bycash);
        easypisa = (LinearLayout)findViewById(R.id.easypisa);
        male = (LinearLayout)findViewById(R.id.male);
        female = (LinearLayout)findViewById(R.id.female);
        txtmale = (TextView) findViewById(R.id.txtmale);
        txtfemale = (TextView) findViewById(R.id.txtfemale);
        searchbus = (TextView) findViewById(R.id.searchbus);
        back = (ImageView) findViewById(R.id.back);
        pickupfrom = (TextView) findViewById(R.id.from);
        topoint = (TextView) findViewById(R.id.to);

        locpick = (TextView) findViewById(R.id.pickup);
        locdrop = (TextView) findViewById(R.id.dropping);



//        pickup = findViewById(R.id.pickup);
//        dropping = findViewById(R.id.dropping);
//        from = findViewById(R.id.from);
//        to = findViewById(R.id.to);
//
//
       SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
       SharedPreferences.Editor editor = pref.edit();
//
        pickupfrom.setText(pref.getString("from", null));
        topoint.setText(pref.getString("to", null));;
       locpick.setText(pref.getString("city", null));
        locdrop.setText(pref.getString("city1", null));
//
//        editor.clear();
//        editor.commit();
//


      //  img1easypasa,img2easypasa

        img1 = (ImageView)findViewById(R.id.img1);
        img2 = (ImageView)findViewById(R.id.img2);

        img1easypasa = (ImageView)findViewById(R.id.img1easypasa);
        img2easypasa = (ImageView)findViewById(R.id.img2easypasa);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img1.setVisibility(View.GONE);
                img2.setVisibility(View.VISIBLE);
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img2.setVisibility(View.GONE);
                img1.setVisibility(View.VISIBLE);
            }
        });


        img1easypasa = (ImageView)findViewById(R.id.img1easypasa);
        img2easypasa = (ImageView)findViewById(R.id.img2easypasa);

        img1easypasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img1easypasa.setVisibility(View.GONE);
                img2easypasa.setVisibility(View.VISIBLE);
            }
        });

        img2easypasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img2easypasa.setVisibility(View.GONE);
                img1easypasa.setVisibility(View.VISIBLE);
            }
        });


        male.setBackgroundResource(R.drawable.rectangle_cure_blue1green);
        female.setBackgroundResource(R.drawable.rectangle_cure_gray);
        txtmale.setTextColor(Color.parseColor("#ffffff"));
        txtfemale.setTextColor(Color.parseColor("#91959d"));


   /*     bycash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                img1.setVisibility(View.GONE);
                img2.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(),"bycash",Toast.LENGTH_LONG).show();
            }
        });

     easypisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                img1easypasa.setVisibility(View.GONE);
                img2easypasa.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(),"easypisa",Toast.LENGTH_LONG).show();
            }
        });*/


        searchbus.setOnClickListener(this);
        male.setOnClickListener(this);
        female.setOnClickListener(this);
        back.setOnClickListener(this);
        easypisa.setOnClickListener(this);
        bycash.setOnClickListener(this);

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

            case R.id.searchbus:
                Intent i = new Intent(context, BingoBusYourTicketBookedActivity.class);
                context.startActivity(i);
                break;

            case R.id.back:
                finish();
                break;

            case R.id.easypisa:

                img1.setVisibility(View.VISIBLE);
                img2.setVisibility(View.GONE);

                img1easypasa.setVisibility(View.GONE);
                img2easypasa.setVisibility(View.VISIBLE);
                break;



            case R.id.bycash:


                img1.setVisibility(View.GONE);
                img2.setVisibility(View.VISIBLE);


                img2easypasa.setVisibility(View.GONE);
                img1easypasa.setVisibility(View.VISIBLE);

                break;



        }


    }
}