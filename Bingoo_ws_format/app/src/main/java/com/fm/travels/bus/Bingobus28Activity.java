package com.fm.travels.bus;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Bingobus28Activity extends AppCompatActivity {

    ImageView cancel;
    TextView from ,to ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingobus28);


        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
//

        from = (TextView) findViewById(R.id.from);
        to = (TextView) findViewById(R.id.to);
        from.setText(pref.getString("from", null));
        to.setText(pref.getString("to", null));
        cancel = (ImageView) findViewById(R.id.cancel);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });


    }
}