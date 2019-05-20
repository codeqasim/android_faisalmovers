package com.faisalmovers.travels.bus;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import Adapter.CustomSpinnerAdapter;
import model.Country;

public class Bingobus_Select_CountryActivity extends AppCompatActivity {

    private Spinner spinner;
    private CustomSpinnerAdapter spinnerAdapter;
    private List<Country> countries = new ArrayList<Country>();
    TextView next,signup;
    ImageView back;
    Animation animation;
    Context context =this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingobus_select_country);


        next = (TextView) findViewById(R.id.next);
        signup = (TextView) findViewById(R.id.signup);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Bingobus_Select_CountryActivity.this, Bingobus24Activity.class);
                startActivity(i);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent i = new Intent(Bingobus_Select_CountryActivity.this, Bingobus_walkthruoghActivity.class);
                startActivity(i);
               /* Intent Profile = new Intent(context,ProfilePerson.class);
                Profile.putExtra("vlaue", "3");
                context.startActivity(Profile);*/
            }
        });


    }
}