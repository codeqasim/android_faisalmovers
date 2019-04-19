package com.faisalmovers.travels.bus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import model.Bingobus7Model;

public class Bingobus28Activity extends AppCompatActivity {

    ImageView cancel;
    TextView from ,to ,price;
    Context context =this;
    Bingobus7Model bingobus7Model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingobus28);
        Intent da = getIntent();
        bingobus7Model = (Bingobus7Model)da.getSerializableExtra("sampleObject");

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
//

        from = (TextView) findViewById(R.id.from);
        to = (TextView) findViewById(R.id.to);
        from.setText(pref.getString("from", null));
        price= (TextView) findViewById(R.id.price);
        to.setText(pref.getString("to", null));
        cancel = (ImageView) findViewById(R.id.cancel);

        price.setText(bingobus7Model.getPrice());

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });


    }
}