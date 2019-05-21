package com.faisalmovers.travels.bus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Splash_screenActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "MyApp_Settings";
    SharedPreferences settings;
    String checker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences preferences=getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);
        String gander = preferences.getString("gander", "");



        if(gander.equals(null))
         {
                gander="0";

         }

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("from", null);
        editor.putString("to", null);
        editor.putString("selectdate",null);
        editor.putString("numberofseat",null);
        editor.putString("seatcount",null);
        editor.putString("gander",gander);
        editor.commit();



        settings = getApplicationContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        checker = settings.getString("show", "");
        Log.d("history",checker);



        Thread background = new Thread() {
            public void run() {

                try {


                    sleep(2 * 1000);


                    Intent intent = new Intent(Splash_screenActivity.this, Bingobus_Select_CountryActivity.class);
                    finish();
                    startActivity(intent);

                /* if(checker.equals("no") || checker.isEmpty())
                    {
                        Intent intent = new Intent(Splash_screenActivity.this, Bingobus_walkthruoghActivity.class);
                        startActivity(intent);
                    } else
                        {


                         Intent intent = new Intent(Splash_screenActivity.this, Bingobus24Activity.class);
                         finish();
                         startActivity(intent);

                    }*/





            /*    Intent intent = new Intent(Splash_screenActivity.this, Bingobus_Select_CountryActivity.class);
                 finish();
                 startActivity(intent);
*/





                } catch (Exception e) {

                }
            }
        };
        // start thread
        background.start();

    }



}
