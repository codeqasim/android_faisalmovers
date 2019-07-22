package com.faisalmovers.travels.bus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import Adapter.Bingobus7Adapter;
import model.Bingobus7Model;
import model.BoardingPoints;
import util.Url;
import util.Utils;

public class Bingobus_Last_StepActivity extends Url implements View.OnClickListener {


    LinearLayout male2, male3, male4, male5, male6;
    LinearLayout female2, female3, female4, female5, female6;
    LinearLayout male, female;//bycash,easypisa;
    TextView txtmale,txtmale2,txtmale3,txtmale4,txtmale5,txtmale6;
    TextView txtfemale,txtfemale2,txtfemale3,txtfemale4,txtfemale5,txtfemale6;
    ImageView img1, img2, back;//,img1easypasa,img2easypasa;
    TextView searchbus;
    Context context = this;
    TextView pickupfrom, topoint, totalamount, totalseatnum;
    TextView locpick, locdrop;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Gson gson;
    ProgressBar progressBar2;
    String firtnamestart = " ";
    EditText nicnumber, fullname;
    ArrayList<Bingobus7Model> historyload = new ArrayList<>();
    String gender2,gender3,gender4,gender5,gender6;
    //Bingobus7Model bingobus7Model;

    LinearLayout layout2, layout3, layout4, layout5, layout6;
    Intent da;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingobus_last_step);


        da = getIntent();
       // bingobus7Model = (Bingobus7Model) da.getSerializableExtra("sampleObject");
        gson = new Gson();
        gnderlayoutfind();

        //bycash = (LinearLayout)findViewById(R.id.bycash);
        // easypisa = (LinearLayout)findViewById(R.id.easypisa);
        male = (LinearLayout) findViewById(R.id.male);
        female = (LinearLayout) findViewById(R.id.female);
        txtmale = (TextView) findViewById(R.id.txtmale);
        txtfemale = (TextView) findViewById(R.id.txtfemale);
        searchbus = (TextView) findViewById(R.id.searchbus);
        back = (ImageView) findViewById(R.id.back);
        pickupfrom = (TextView) findViewById(R.id.from);
        topoint = (TextView) findViewById(R.id.to);

        locpick = (TextView) findViewById(R.id.pickup);
        locdrop = (TextView) findViewById(R.id.dropping);

        totalamount = (TextView) findViewById(R.id.total);
        totalseatnum = (TextView) findViewById(R.id.seatnum);


        nicnumber = (EditText) findViewById(R.id.nicnumber);
        fullname = (EditText) findViewById(R.id.fullname);

        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar2.setVisibility(View.GONE);


//        pickup = findViewById(R.id.pickup);
//        dropping = findViewById(R.id.dropping);
//        from = findViewById(R.id.from);
//        to = findViewById(R.id.to);
//
//

       /* findViewById(R.id.nicnumber).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
*/
        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        editor = pref.edit();
//
        pickupfrom.setText(pref.getString("from", null));
        topoint.setText(pref.getString("to", null));
        ;
        locpick.setText(pref.getString("city", null));
        /* locdrop.setText(pref.getString("city1", null));*/
        locdrop.setText(pref.getString("to", null));
        totalamount.setText("TOTAL : Rs " + pref.getString("totalamountofseat", null));
        totalseatnum.setText("SeatNo = { " + pref.getString("numberofseat", null) + " }");
        String mobileverificationefullname = pref.getString("name", null);
        String mobileverificationenic = pref.getString("nic", null);

        fullname.setText(mobileverificationefullname);
        nicnumber.setText(mobileverificationenic);
//
//        editor.clear();
//        editor.commit();
//


        //  img1easypasa,img2easypasa

       /* img1 = (ImageView)findViewById(R.id.img1);
        img2 = (ImageView)findViewById(R.id.img2);

        img1easypasa = (ImageView)findViewById(R.id.img1easypasa);
        img2easypasa = (ImageView)findViewById(R.id.img2easypasa);*/
/*

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
*/

/*
        img1easypasa = (ImageView)findViewById(R.id.img1easypasa);
        img2easypasa = (ImageView)findViewById(R.id.img2easypasa);*/

       /* img1easypasa.setOnClickListener(new View.OnClickListener() {
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
        });*/


        String gander = pref.getString("gander", null);

        if (gander.equals("1")) {
            female.setBackgroundResource(R.drawable.rectangle_cure_blue1green);
            male.setBackgroundResource(R.drawable.rectangle_cure_gray);
            txtfemale.setTextColor(Color.parseColor("#ffffff"));
            txtmale.setTextColor(Color.parseColor("#91959d"));
            editor.putString("gander", "1");
            editor.commit();

        } else if (gander.equals("2")) {

            male.setBackgroundResource(R.drawable.rectangle_cure_blue1green);
            female.setBackgroundResource(R.drawable.rectangle_cure_gray);
            txtmale.setTextColor(Color.parseColor("#ffffff"));
            txtfemale.setTextColor(Color.parseColor("#91959d"));
            editor.putString("gander", "2");
            editor.commit();
        } else {

            male.setBackgroundResource(R.drawable.rectangle_cure_blue1green);
            female.setBackgroundResource(R.drawable.rectangle_cure_gray);
            txtmale.setTextColor(Color.parseColor("#ffffff"));
            txtfemale.setTextColor(Color.parseColor("#91959d"));
            editor.putString("gander", "2");
            editor.commit();




        }


        searchbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("malikkkkkkkkk", " " + fullname.getText());
                fullname.getText();
                if (fullname.getText().toString().trim().equals("")) {
                    Utils.showErrorToast(getApplicationContext(), " required info  ");
                } else if (nicnumber.getText().toString().trim().equals("")) {
                    Utils.showErrorToast(getApplicationContext(), " required info  ");
                } else {


                 /*   searchbus.setVisibility(View.GONE);
                    progressBar2.setVisibility(View.VISIBLE);
                    bookseatintway();*/
                }
            }
        });
        male.setOnClickListener(this);
        female.setOnClickListener(this);

        male2.setOnClickListener(this);
        female2.setOnClickListener(this);

        male3.setOnClickListener(this);
        female3.setOnClickListener(this);

        male4.setOnClickListener(this);
        female4.setOnClickListener(this);

        male5.setOnClickListener(this);
        female5.setOnClickListener(this);

        male6.setOnClickListener(this);
        female6.setOnClickListener(this);

        back.setOnClickListener(this);
        //   easypisa.setOnClickListener(this);
        //   bycash.setOnClickListener(this);

     /*   img1.setVisibility(View.GONE);
        img2.setVisibility(View.VISIBLE);
        img2easypasa.setVisibility(View.GONE);
        img1easypasa.setVisibility(View.VISIBLE);
*/


        //   loadhistorydata();


        String seatcount = pref.getString("seatcount", null);
        int setcounter = Integer.parseInt(seatcount);
        layoutset(6);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.male:
                male.setBackgroundResource(R.drawable.rectangle_cure_blue1green);
                female.setBackgroundResource(R.drawable.rectangle_cure_gray);
                txtmale.setTextColor(Color.parseColor("#ffffff"));
                txtfemale.setTextColor(Color.parseColor("#91959d"));
                editor.putString("gander", "2");
                editor.commit();
                break;

            case R.id.female:
                female.setBackgroundResource(R.drawable.rectangle_cure_blue1green);
                male.setBackgroundResource(R.drawable.rectangle_cure_gray);
                txtfemale.setTextColor(Color.parseColor("#ffffff"));
                txtmale.setTextColor(Color.parseColor("#91959d"));
                editor.putString("gander", "1");
                editor.commit();
                break;


            case R.id.male2:
                male2.setBackgroundResource(R.drawable.rectangle_cure_blue1green);
                female2.setBackgroundResource(R.drawable.rectangle_cure_gray);
                txtmale2.setTextColor(Color.parseColor("#ffffff"));
                txtfemale2.setTextColor(Color.parseColor("#91959d"));
                gender2="male";
                break;

            case R.id.female2:
                female2.setBackgroundResource(R.drawable.rectangle_cure_blue1green);
                male2.setBackgroundResource(R.drawable.rectangle_cure_gray);
                txtfemale2.setTextColor(Color.parseColor("#ffffff"));
                txtmale2.setTextColor(Color.parseColor("#91959d"));
                gender2="female";
                break;


            case R.id.male3:
                male3.setBackgroundResource(R.drawable.rectangle_cure_blue1green);
                female3.setBackgroundResource(R.drawable.rectangle_cure_gray);
                txtmale3.setTextColor(Color.parseColor("#ffffff"));
                txtfemale3.setTextColor(Color.parseColor("#91959d"));
                gender3="male";
                break;

            case R.id.female3:
                female3.setBackgroundResource(R.drawable.rectangle_cure_blue1green);
                male3.setBackgroundResource(R.drawable.rectangle_cure_gray);
                txtfemale3.setTextColor(Color.parseColor("#ffffff"));
                txtmale3.setTextColor(Color.parseColor("#91959d"));
                gender3="female";
                break;


            case R.id.male4:
                male4.setBackgroundResource(R.drawable.rectangle_cure_blue1green);
                female4.setBackgroundResource(R.drawable.rectangle_cure_gray);
                txtmale4.setTextColor(Color.parseColor("#ffffff"));
                txtfemale4.setTextColor(Color.parseColor("#91959d"));
                gender4="male";
                break;

            case R.id.female4:
                female4.setBackgroundResource(R.drawable.rectangle_cure_blue1green);
                male4.setBackgroundResource(R.drawable.rectangle_cure_gray);
                txtfemale4.setTextColor(Color.parseColor("#ffffff"));
                txtmale4.setTextColor(Color.parseColor("#91959d"));
                gender4="female";
                break;


            case R.id.male5:
                male5.setBackgroundResource(R.drawable.rectangle_cure_blue1green);
                female5.setBackgroundResource(R.drawable.rectangle_cure_gray);
                txtmale5.setTextColor(Color.parseColor("#ffffff"));
                txtfemale5.setTextColor(Color.parseColor("#91959d"));
                gender5="male";
                break;

            case R.id.female5:
                female5.setBackgroundResource(R.drawable.rectangle_cure_blue1green);
                male5.setBackgroundResource(R.drawable.rectangle_cure_gray);
                txtfemale5.setTextColor(Color.parseColor("#ffffff"));
                txtmale5.setTextColor(Color.parseColor("#91959d"));
                gender5="female";
                break;



            case R.id.male6:
                male6.setBackgroundResource(R.drawable.rectangle_cure_blue1green);
                female6.setBackgroundResource(R.drawable.rectangle_cure_gray);
                txtmale6.setTextColor(Color.parseColor("#ffffff"));
                txtfemale6.setTextColor(Color.parseColor("#91959d"));
                gender6="male";
                break;

            case R.id.female6:
                female6.setBackgroundResource(R.drawable.rectangle_cure_blue1green);
                male6.setBackgroundResource(R.drawable.rectangle_cure_gray);
                txtfemale6.setTextColor(Color.parseColor("#ffffff"));
                txtmale6.setTextColor(Color.parseColor("#91959d"));
                gender6="female";
                break;



            case R.id.back:
                finish();
                break;




        }


    }

    public void postrequest(final String depdate, String url, final String seatlist, final String toCityId, final String fromCityId, final String mNumber, final String email, final Bingobus7Model bm, final String id, final String nic, final String passenger_form, final String boardingpoint, final String coupon_id) {
        mRequestQueue = Volley.newRequestQueue(this);
        mStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Log.d("bordingpoint2", "response/" + response);

                try {
                    JSONObject jsonObject1 = new JSONObject(response);
                    JSONObject json_object = jsonObject1.getJSONObject("response");

                    if (json_object.getString("error").equals("no")) {


                        seatsnull();
                        String url = json_object.getString("url");
                        String booking_id = json_object.getString("booking_id");
                        // addhistory(bm);
                        progressBar2.setVisibility(View.GONE);
                        //  Utils.showSuccesToast(getApplicationContext(),"seat BOOKED");
                        Intent intent1 = new Intent(getApplicationContext(), Webviewinvoice.class);
                        intent1.putExtra("weburl", url);
                        startActivity(intent1);


                    } else {


                        Utils.showErrorToast(getApplicationContext(), json_object.getString("msg"));
                        progressBar2.setVisibility(View.GONE);
                       /* Toast.makeText(getApplicationContext(), json_object.getString("msg"), Toast.LENGTH_LONG).show();
                        finish();*/
                    }


                } catch (JSONException e) {
                    e.printStackTrace();


                    Utils.showErrorToast(getApplicationContext(), "server issue");
                    progressBar2.setVisibility(View.GONE);
                }

            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {

                searchbus.setVisibility(View.VISIBLE);
                searchbus.setText("Try again");
                Log.d("bordingpoint2", "error/" + error);
                Utils.showErrorToast(getApplicationContext(), "server issue");
                progressBar2.setVisibility(View.GONE);
                //This code is executed if there is an error.
            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("seatlist", seatlist);
                params.put("opid", bm.getOpId());
                params.put("notifyable_user_email", email);
                params.put("notifyable_user_mobile", mNumber);
                params.put("schid", bm.getScheduleID());
                params.put("depdate", depdate);
                params.put("deptime", bm.getQuerydepartureTime2());
                params.put("passenger_form", passenger_form);
                params.put("previousBookingId", "0");
                params.put("fromcity", fromCityId);
                params.put("tocity", toCityId);
                params.put("couponid", coupon_id);
                params.put("userId", id);
                params.put("nic", nic);
                params.put("boardingpoint", boardingpoint);

                String g = gson.toJson(params);
                Log.d("boardingpoint", g);
                return params;
            }
        };

        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(mStringRequest);

    }

    public void bookseatintway() {
        String fromCityId = pref.getString("fromcityid", null);
        String toCityId = pref.getString("tocityid", null);
        String selectdate = pref.getString("selectdate", null);
        String seatlist = pref.getString("seatlist", null);
        String mobileverification = pref.getString("mobileverification", null);
        String bordingpoint = pref.getString("boardingpointid", null);
        String mobileverificationemail = pref.getString("email", null);
        String mobileverificationefullname = pref.getString("name", null);
        String mobileverificationenic = pref.getString("nic", null);
        String gander = pref.getString("gander", null);
        String id = pref.getString("id", " ");

        Log.d("gandergander", " ///" + gander);


        if (gander.equals("2")) {
            gander = "male";
            firtnamestart = "Mr ";


        } else {
            gander = "female";
            firtnamestart = "Miss ";

        }


        JsonObject jsObj = new JsonObject();
        jsObj.addProperty("age", "29");
        jsObj.addProperty("email", mobileverificationemail);
        jsObj.addProperty("first_name", mobileverificationefullname);
        jsObj.addProperty("gender", gander);
        jsObj.addProperty("last_name", " ");
        jsObj.addProperty("nic", mobileverificationenic);

        String g = gson.toJson(jsObj);
        g = "[" + g + "]";
        Log.d("gggggg", " " + g);
        String url = urlseatbook;
//        postrequest(selectdate, url, seatlist, toCityId, fromCityId, mobileverification, mobileverificationemail, bingobus7Model, id, mobileverificationenic, g, bordingpoint, "0");

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("activitycheck", "On Restart .....");
    }

    /* (non-Javadoc)
     * @see android.app.Activity#onResume()
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("activitycheck", "On Resume .....");
    }


    public void seatsnull() {
        editor.putString("seatlist", " ");
        editor.commit();
    }


    public void layoutset(int seatcount) {

        gnderlayoutfind();
        layout2 = findViewById(R.id.layout2);
        layout3 = findViewById(R.id.layout3);
        layout4 = findViewById(R.id.layout4);
        layout5 = findViewById(R.id.layout5);
        layout6 = findViewById(R.id.layout6);
        layout2.setVisibility(View.GONE);
        layout3.setVisibility(View.GONE);
        layout4.setVisibility(View.GONE);
        layout5.setVisibility(View.GONE);
        layout6.setVisibility(View.GONE);
        if (seatcount == 2) {
            layout2.setVisibility(View.VISIBLE);

        } else if (seatcount == 3) {
            layout2.setVisibility(View.VISIBLE);
            layout3.setVisibility(View.VISIBLE);





        } else if (seatcount == 4) {
            layout2.setVisibility(View.VISIBLE);
            layout3.setVisibility(View.VISIBLE);
            layout4.setVisibility(View.VISIBLE);





        } else if (seatcount == 5) {
            layout2.setVisibility(View.VISIBLE);
            layout3.setVisibility(View.VISIBLE);
            layout4.setVisibility(View.VISIBLE);
            layout5.setVisibility(View.VISIBLE);



        }
        else if (seatcount == 6) {
            layout2.setVisibility(View.VISIBLE);
            layout3.setVisibility(View.VISIBLE);
            layout4.setVisibility(View.VISIBLE);
            layout5.setVisibility(View.VISIBLE);
            layout6.setVisibility(View.VISIBLE);






        }

        else {
            layout2.setVisibility(View.GONE);
            layout3.setVisibility(View.GONE);
            layout4.setVisibility(View.GONE);
            layout5.setVisibility(View.GONE);
            layout6.setVisibility(View.GONE);
        }





        //   if ()


    }

    public void gnderlayoutfind() {


         txtmale2=findViewById(R.id.txtmale2);
         txtmale3=findViewById(R.id.txtmale3);
         txtmale4=findViewById(R.id.txtmale4);
         txtmale5=findViewById(R.id.txtmale5);
         txtmale6=findViewById(R.id.txtmale6);


        txtfemale2=findViewById(R.id.txtfemale2);
        txtfemale3=findViewById(R.id.txtfemale3);
        txtfemale4=findViewById(R.id.txtfemale4);
        txtfemale5=findViewById(R.id.txtfemale5);
        txtfemale6=findViewById(R.id.txtfemale6);;





        male2 = (LinearLayout) findViewById(R.id.male2);
        female2 = (LinearLayout) findViewById(R.id.female2);


        male3 = (LinearLayout) findViewById(R.id.male3);
        female3 = (LinearLayout) findViewById(R.id.female3);


        male4 = (LinearLayout) findViewById(R.id.male4);
        female4 = (LinearLayout) findViewById(R.id.female4);


        male5 = (LinearLayout) findViewById(R.id.male5);
        female5 = (LinearLayout) findViewById(R.id.female5);


        male6 = (LinearLayout) findViewById(R.id.male6);
        female6 = (LinearLayout) findViewById(R.id.female6);



        male6.setBackgroundResource(R.drawable.rectangle_cure_blue1green);
        female6.setBackgroundResource(R.drawable.rectangle_cure_gray);
        txtmale6.setTextColor(Color.parseColor("#ffffff"));
        txtfemale6.setTextColor(Color.parseColor("#91959d"));
        gender6="male";
        male5.setBackgroundResource(R.drawable.rectangle_cure_blue1green);
        female5.setBackgroundResource(R.drawable.rectangle_cure_gray);
        txtmale5.setTextColor(Color.parseColor("#ffffff"));
        txtfemale5.setTextColor(Color.parseColor("#91959d"));
        gender5="male";
        male4.setBackgroundResource(R.drawable.rectangle_cure_blue1green);
        female4.setBackgroundResource(R.drawable.rectangle_cure_gray);
        txtmale4.setTextColor(Color.parseColor("#ffffff"));
        txtfemale4.setTextColor(Color.parseColor("#91959d"));
        gender4="male";
        male3.setBackgroundResource(R.drawable.rectangle_cure_blue1green);
        female3.setBackgroundResource(R.drawable.rectangle_cure_gray);
        txtmale3.setTextColor(Color.parseColor("#ffffff"));
        txtfemale3.setTextColor(Color.parseColor("#91959d"));
        gender3="male";
        male2.setBackgroundResource(R.drawable.rectangle_cure_blue1green);
        female2.setBackgroundResource(R.drawable.rectangle_cure_gray);
        txtmale2.setTextColor(Color.parseColor("#ffffff"));
        txtfemale2.setTextColor(Color.parseColor("#91959d"));
        gender2="male";
    }
}