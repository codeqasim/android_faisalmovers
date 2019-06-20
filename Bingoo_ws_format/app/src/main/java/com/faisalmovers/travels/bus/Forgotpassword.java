package com.faisalmovers.travels.bus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class Forgotpassword extends AppCompatActivity {



    ProgressBar progressBar2;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);



        progressBar2 = findViewById(R.id.progressBar2);
        progressBar2.setVisibility(View.GONE);
    }


    public  void summitpassword(View view)
    {

    }

    public  void backscreen(View view)
    {
        finish();
    }
}
