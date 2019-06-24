package com.faisalmovers.travels.bus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Adapter.CustomerAdapter;
import model.Cities;
import model.Customer;
import util.AppStatus;
import util.Url;
import util.Utils;

public class Splash_screenActivity extends Url {



    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    public static final String PREFS_NAME = "MyApp_Settings";
    SharedPreferences settings;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String checker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

         preferences=getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);
        String gander = preferences.getString("gander", "");



        if(gander.equals(null))
         {
                gander="0";

         }

        editor = preferences.edit();
        editor.putString("from", null);
        editor.putString("to", null);
        editor.putString("selectdate",null);
        editor.putString("numberofseat","0");
        editor.putString("seatcount",null);
        editor.putString("gander",gander);
        editor.putString("seatlist"," ");
        editor.commit();



        settings = getApplicationContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        checker = settings.getString("show", "");
        Log.d("history",checker);




        if (AppStatus.getInstance(this).isOnline()) {


            sendAndRequestResponse(cityweb);


        } else {

            Utils.isNetworkAvailable(getApplicationContext());
        }

        //runfuncation();


    }


    private void sendAndRequestResponse(String url) {




        mRequestQueue = Volley.newRequestQueue(this);
        mStringRequest = new StringRequest(Request.Method.GET, "https://www.bookkaru.com/api/bus/cities?appKey=bookkaru", new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Log.d("checkenamecity","///"+response);
                 editor = preferences.edit();
                editor.putString("citynamedata", " ");
                editor.commit();
                runfuncation();


            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Utils.isNetworkAvailable(getApplicationContext());
               Log.d("checkenamecity",error.getMessage());
                runfuncation();

            }
        });
        mRequestQueue.add(mStringRequest);

    }



    public void runfuncation()
    {
        Thread background = new Thread() {
            public void run() {

                try {


                    sleep(1 * 2000);


                    if(checker.equals("no") || checker.isEmpty())
                    {
                        Intent intent = new Intent(Splash_screenActivity.this, Bingobus_Select_CountryActivity.class);
                        finish();
                        startActivity(intent);
                    } else
                    {


                        Intent intent = new Intent(Splash_screenActivity.this, Bingobus24Activity.class);
                        finish();
                        startActivity(intent);

                    }



                   /* Intent intent = new Intent(Splash_screenActivity.this, Webviewinvoice.class);
                   intent.putExtra("weburl","https://www.google.com/search?q=google&rlz=1C5CHFA_enPK767PK767&oq=goo&aqs=chrome.0.69i59j69i60j69i57j69i65l2j69i59l2.1799j0j7&sourceid=chrome&ie=UTF-8");
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
