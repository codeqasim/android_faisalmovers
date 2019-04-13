package com.fm.travels.bus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class BingoBusBookingDetailActivity extends AppCompatActivity {

    ImageView detailsback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingo_bus_booking_detail);

        detailsback = (ImageView) findViewById(R.id.detailsback);
        detailsback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}
