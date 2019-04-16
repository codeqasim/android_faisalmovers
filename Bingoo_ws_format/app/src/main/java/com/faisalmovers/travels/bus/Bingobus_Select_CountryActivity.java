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
    TextView next;
    ImageView back;
    Animation animation;
    Context context =this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingobus_select_country);

        spinner = (Spinner) findViewById(R.id.spinner);
        next = (TextView) findViewById(R.id.next);
        back = (ImageView) findViewById(R.id.back);
        spinnerAdapter = new CustomSpinnerAdapter(this, countries );
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // do something after selected item here
                Country country = countries.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, Bingobus_walkthruoghActivity.class);
                context.startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // finish();
            }
        });


        populateCountries();
    }
    private void populateCountries() {
        Country pak = new Country();
        pak.name = "PAKISTAN";
        pak.flag = R.drawable.flag_pak;

        Country indonesia = new Country();

        indonesia.name = "INDONESIA";
        indonesia.flag = R.drawable.flag_indonesia;
        Country usa = new Country();
        usa.name = "USA";
        usa.flag = R.drawable.flag_usa;
        Country germany = new Country();
        germany.name = "GERMANY";
        germany.flag = R.drawable.flag_germany;
        Country china = new Country();
        china.name = "CHINA";
        china.flag = R.drawable.flag_china;
        Country india = new Country();
        india.name = "INDIA";
        india.flag = R.drawable.flag_india;


        countries.add(pak);
        countries.add(indonesia);
        countries.add(usa);
        countries.add(germany);
        countries.add(china);
        countries.add(india);
        spinnerAdapter.notifyDataSetChanged();
    }
    }