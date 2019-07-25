package com.faisalmovers.travels.bus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
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


    String mobileverificationefullname,mobileverificationemail,mobileverificationenic,gander,id;
    String gender2,gender3,gender4,gender5,gender6;
    JSONArray jsonArray = new JSONArray();
    JSONObject PASSENGER1,PASSENGER2,PASSENGER3,PASSENGER4,PASSENGER5,PASSENGER6;
    LinearLayout layout2, layout3, layout4, layout5, layout6;
    LinearLayout male2, male3, male4, male5, male6;
    LinearLayout female2, female3, female4, female5, female6;
    EditText fullname2,fullname3,fullname4,fullname5,fullname6;
    EditText nicnumber2,nicnumber3,nicnumber4,nicnumber5,nicnumber6;
    EditText age2,age3,age4,age5,age6;
    LinearLayout male, female;//bycash,easypisa;
    TextView txtmale,txtmale2,txtmale3,txtmale4,txtmale5,txtmale6;
    TextView txtfemale,txtfemale2,txtfemale3,txtfemale4,txtfemale5,txtfemale6;
    ImageView img1, img2, back;//,img1easypasa,img2easypasa;
    TextView searchbus;
    Context context=this;
    TextView pickupfrom,topoint,totalamount ,totalseatnum;
    TextView locpick,locdrop;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Gson gson;
    ProgressBar progressBar2;
    String firtnamestart=" ";
    EditText nicnumber,fullname;
    ArrayList<Bingobus7Model> historyload = new ArrayList<>();
    int setcounter;
    Bingobus7Model bingobus7Model;
    Intent da;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingobus_last_step);


        da = getIntent();
        bingobus7Model = (Bingobus7Model)da.getSerializableExtra("sampleObject");
        gson = new Gson();
        //bycash = (LinearLayout)findViewById(R.id.bycash);
        // easypisa = (LinearLayout)findViewById(R.id.easypisa);
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
        topoint.setText(pref.getString("to", null));;
        locpick.setText(pref.getString("city", null));
        /* locdrop.setText(pref.getString("city1", null));*/
        locdrop.setText(pref.getString("to", null) +"- " +bingobus7Model.getArrivalTime());
        totalamount.setText("TOTAL : Rs "+pref.getString("totalamountofseat", null));
        totalseatnum.setText( "SeatNo = { "+ pref.getString("numberofseat", null) +" }");
         mobileverificationefullname =pref.getString("name",null);
         mobileverificationenic =pref.getString("nic",null);

        fullname.setText(mobileverificationefullname);
        nicnumber.setText(mobileverificationenic);
        disableInput(fullname);
        disableInput(nicnumber);
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


        gander =pref.getString("gander",null);
        mobileverification = pref.getString("mobileverification",null);
        mobileverificationemail =pref.getString("email",null);
        mobileverificationefullname =pref.getString("name",null);
        mobileverificationenic =pref.getString("nic",null);
        gander =pref.getString("gander",null);
        id =pref.getString("id"," ");

        if(gander.equals("1"))
        {
            female.setBackgroundResource(R.drawable.rectangle_cure_blue1green);
            male.setBackgroundResource(R.drawable.rectangle_cure_gray);
            txtfemale.setTextColor(Color.parseColor("#ffffff"));
            txtmale.setTextColor(Color.parseColor("#91959d"));
            editor.putString("gander", "1");
            editor.commit();

        }else if(gander.equals("2"))
        {

            male.setBackgroundResource(R.drawable.rectangle_cure_blue1green);
            female.setBackgroundResource(R.drawable.rectangle_cure_gray);
            txtmale.setTextColor(Color.parseColor("#ffffff"));
            txtfemale.setTextColor(Color.parseColor("#91959d"));
            editor.putString("gander", "2");
            editor.commit();
        }
        else {

            male.setBackgroundResource(R.drawable.rectangle_cure_blue1green);
            female.setBackgroundResource(R.drawable.rectangle_cure_gray);
            txtmale.setTextColor(Color.parseColor("#ffffff"));
            txtfemale.setTextColor(Color.parseColor("#91959d"));
            editor.putString("gander", "2");
            editor.commit();
        }

        String seatcount = pref.getString("seatcount", null);
        setcounter = Integer.parseInt(seatcount);
        layoutset(setcounter);



        searchbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Boolean vailddata=validtions(setcounter);
                Log.d("gggg", " " + vailddata);
                if (vailddata==true)
                {

                    searchbus.setVisibility(View.GONE);
                    progressBar2.setVisibility(View.VISIBLE);
                    bookseatintway();
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

            /*     case R.id.easypisa:

             *//*   img1.setVisibility(View.VISIBLE);
                img2.setVisibility(View.GONE);

                img1easypasa.setVisibility(View.GONE);
                img2easypasa.setVisibility(View.VISIBLE);*//*
                break;



            case R.id.bycash:


               *//* img1.setVisibility(View.GONE);
                img2.setVisibility(View.VISIBLE);


                img2easypasa.setVisibility(View.GONE);
                img1easypasa.setVisibility(View.VISIBLE);
*//*
                break;*/



        }


    }

    public void postrequest(final String depdate , String url , final String seatlist , final String toCityId , final String fromCityId, final String mNumber, final String email, final Bingobus7Model bm, final String id, final String nic, final String passenger_form, final String boardingpoint, final String coupon_id)
    {
        mRequestQueue = Volley.newRequestQueue(this);
        mStringRequest  = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Log.d("bordingpoint2","response/" +response);

                try
                {
                    JSONObject jsonObject1 = new JSONObject(response);
                    JSONObject json_object = jsonObject1.getJSONObject("response");

                    if (json_object.getString("error").equals("no")) {




                        seatsnull();
                        String url = json_object.getString("url");
                        String booking_id = json_object.getString("booking_id");
                        // addhistory(bm);
                        progressBar2.setVisibility(View.GONE);
                        //Utils.showSuccesToast(getApplicationContext(),"seat Hold");
                        Intent intent1 = new Intent(getApplicationContext(),Webviewinvoice.class);
                        intent1.putExtra("weburl",url);
                        startActivity(intent1);





                    }else{


                        searchbus.setVisibility(View.VISIBLE);
                        searchbus.setText("Try again");
                        Utils.showErrorToast(getApplicationContext(), json_object.getString("msg"));
                        progressBar2.setVisibility(View.GONE);
                       /* Toast.makeText(getApplicationContext(), json_object.getString("msg"), Toast.LENGTH_LONG).show();
                        finish();*/
                    }


                }
                catch (JSONException e) {
                    e.printStackTrace();


                    Utils.showErrorToast(getApplicationContext(),"server issue");
                    progressBar2.setVisibility(View.GONE);
                }

            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {

                searchbus.setVisibility(View.VISIBLE);
                searchbus.setText("Try again");
                Log.d("bordingpoint2","error/" +error);
                Utils.showErrorToast(getApplicationContext(),"server issue");
                progressBar2.setVisibility(View.GONE);
                //This code is executed if there is an error.
            }
        }) {
            @Override
            protected Map<String, String> getParams()  {

                Map<String, String> params = new HashMap<String, String>();
                params.put("seatlist",seatlist);
                params.put("opid",bm.getOpId());
                params.put("notifyable_user_email",email);
                params.put("notifyable_user_mobile",mNumber);
                params.put("schid",bm.getScheduleID());
                params.put("depdate",depdate);
                params.put("deptime",bm.getQuerydepartureTime2());
                params.put("passenger_form",passenger_form);
                params.put("previousBookingId","0");
                params.put("fromcity",fromCityId);
                params.put("tocity",toCityId);
                params.put("couponid",coupon_id);
                params.put("userId",id);
                params.put("nic",nic);
                params.put("boardingpoint",boardingpoint);

                String g= gson.toJson(params);
                Log.d("boardingpoint",g);
                return params;
            }
        };

        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(mStringRequest);

    }

    public void bookseatintway()
    {

        String fromCityId = pref.getString("fromcityid",null);
        String toCityId =  pref.getString("tocityid",null);
        String selectdate =pref.getString("selectdate",null);
        String seatlist =pref.getString("seatlist",null);
        String bordingpoint = pref.getString("boardingpointid", null);


        Log.d("gandergander"," ///"+gander);


        if(gander.equals("2"))
        {
            gander="male";
            firtnamestart ="Mr ";


        }else {
            gander ="female";
            firtnamestart = "Miss ";

        }





      JsonObject jsObj = new JsonObject();
        jsObj.addProperty("age", "29");
        jsObj.addProperty("email", mobileverificationemail);
        jsObj.addProperty("first_name", mobileverificationefullname);
        jsObj.addProperty("gender", gander);
        jsObj.addProperty("last_name", " ");
        jsObj.addProperty("nic", mobileverificationenic);

        String passenger_form = jsonArray.toString();;
        String url = urlseatbook;


        postrequest(selectdate,url,seatlist ,toCityId,fromCityId,mobileverification,mobileverificationemail,bingobus7Model,id,mobileverificationenic ,passenger_form,bordingpoint,"0");

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



    public  void seatsnull()
    {
        editor.putString("seatlist"," ");
        editor.commit();
    }

    public void layoutset(int seatcount) {

        gnderlayoutfind();
        // validtions(seatcount);
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


       // loaddata();
    }

    void disableInput(EditText editText){
        editText.setInputType(InputType.TYPE_NULL);
        editText.setTextIsSelectable(false);
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                return true;  // Blocks input from hardware keyboards.
            }
        });
    }

    public  Boolean validtions(int seatcount)
    {

        Boolean checker=false;
        fullname2 = findViewById(R.id.fullname2);
        fullname3 = findViewById(R.id.fullname3);
        fullname4 = findViewById(R.id.fullname4);
        fullname5 = findViewById(R.id.fullname5);
        fullname6 = findViewById(R.id.fullname6);

        nicnumber2 = findViewById(R.id.nicnumber2);
        nicnumber3 = findViewById(R.id.nicnumber3);
        nicnumber4 = findViewById(R.id.nicnumber4);
        nicnumber5 = findViewById(R.id.nicnumber5);
        nicnumber6 = findViewById(R.id.nicnumber6);

        age2 = findViewById(R.id.age2);
        age3 = findViewById(R.id.age3);
        age4 = findViewById(R.id.age4);
        age5 = findViewById(R.id.age5);
        age6 = findViewById(R.id.age6);



        if (seatcount == 1) {

            if (fullname.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            } else if (nicnumber.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            } else {

                checker=true;
                make_jsonobject_users(1 );

                 /*   searchbus.setVisibility(View.GONE);
                    progressBar2.setVisibility(View.VISIBLE);
                    bookseatintway();*/
            }




        }
        else if (seatcount == 2) {

            if (fullname2.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            } else if (nicnumber2.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            }
            else if (age2.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            }else
            {
                checker=true;



                make_jsonobject_users(2 );


            }

        }
        else if(seatcount == 3)
        {
            if (fullname2.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            } else if (nicnumber2.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            }
            else if (age2.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            }

            else if (fullname3.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            } else if (nicnumber3.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            }
            else if (age3.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            }
            else
            {
                checker=true;
                make_jsonobject_users(3 );
            }
        }else if(seatcount == 4)
        {
            if (fullname2.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            } else if (nicnumber2.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            }
            else if (age2.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            }

            else if (fullname3.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            } else if (nicnumber3.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            }
            else if (age3.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            }

            else if(fullname4.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            } else if (nicnumber4.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            }
            else if (age4.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            }
            else
            {
                checker=true;
                make_jsonobject_users(4 );
            }
        }
        else if(seatcount == 5)
        {
            if (fullname2.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            } else if (nicnumber2.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            }
            else if (age2.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            }

            else if (fullname3.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            } else if (nicnumber3.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            }
            else if (age3.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            }

            else if (fullname4.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            } else if (nicnumber4.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            }
            else if (age4.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            }

            else if (fullname5.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            } else if (nicnumber5.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            }
            else if (age5.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            }
            else
            {
                checker=true;
                make_jsonobject_users(5 );
            }
        }

        else if(seatcount == 6)
        {
            if (fullname2.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            } else if (nicnumber2.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            }
            else if (age2.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            }

            else if (fullname3.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            } else if (nicnumber3.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            }
            else if (age3.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            }

            else if (fullname4.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            } else if (nicnumber4.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            }
            else if (age4.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            }
            else if (fullname5.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            } else if (nicnumber5.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            }
            else if (age5.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            } else if (fullname6.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            } else if (nicnumber6.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            }
            else if (age6.getText().toString().trim().equals("")) {
                Utils.showErrorToast(getApplicationContext(), " required info  ");
            }else
            {
                checker=true;
                make_jsonobject_users(6);
            }


        }


        return checker;
    }

    public void make_jsonobject_users(int position )
    {


        String full_name2 =""+fullname2.getText();
        String full_name3=""+fullname3 .getText();
        String full_name4=""+fullname4 .getText();
        String full_name5=""+fullname5 .getText();
        String full_name6=""+fullname6 .getText();

        String nic_number2=""+nicnumber2.getText();
        String nic_number3=""+nicnumber3.getText();
        String nic_number4=""+nicnumber4.getText();
        String nic_number5=""+nicnumber5.getText();
        String nic_number6=""+nicnumber6.getText();

        String age__2=""+age2.getText();
        String age_3=""+age3.getText();
        String age_4=""+age4.getText();
        String age_5=""+age5.getText();
        String age_6=""+age6.getText();

        PASSENGER1 = new JSONObject();
        PASSENGER2 = new JSONObject();
        PASSENGER3 = new JSONObject();
        PASSENGER4 = new JSONObject();
        PASSENGER5 = new JSONObject();
        PASSENGER6 = new JSONObject();


        for (int i =0;i<=position;i++)
        {

            if (i==1)
            {

                try {

                    PASSENGER1.put("age", "29");
                    PASSENGER1.put("email", mobileverificationemail);
                    PASSENGER1.put("first_name", mobileverificationefullname);
                    PASSENGER1.put("gender", gander);
                    PASSENGER1.put("last_name", " ");
                    PASSENGER1.put("nic", mobileverificationenic);
                    jsonArray.put(PASSENGER1);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }



            }

            else   if (i==2)
            {

                try {

                    PASSENGER2.put("age", age__2);
                    PASSENGER2.put("email", mobileverificationemail);
                    PASSENGER2.put("first_name", full_name2);
                    PASSENGER2.put("gender", gender2);
                    PASSENGER2.put("last_name", " ");
                    PASSENGER2.put("nic", nic_number2);
                    jsonArray.put(PASSENGER2);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }



            } else if(i==3)
            {


                try {

                    PASSENGER3.put("age", age_3);
                    PASSENGER3.put("email", mobileverificationemail);
                    PASSENGER3.put("first_name", full_name3);
                    PASSENGER3.put("gender", gender3);
                    PASSENGER3.put("last_name", " ");
                    PASSENGER3.put("nic", nic_number3);
                    jsonArray.put(PASSENGER3);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


            }
            else if (i==4)
            {

                try {

                    PASSENGER4.put("age", age_4);
                    PASSENGER4.put("email", mobileverificationemail);
                    PASSENGER4.put("first_name", full_name4);
                    PASSENGER4.put("gender", gender4);
                    PASSENGER4.put("last_name", " ");
                    PASSENGER4.put("nic", nic_number4);
                    jsonArray.put(PASSENGER4);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }



            }
            else  if(i==5)
            {

                try {

                    PASSENGER5.put("age", age_5);
                    PASSENGER5.put("email", mobileverificationemail);
                    PASSENGER5.put("first_name", full_name5);
                    PASSENGER5.put("gender", gender5);
                    PASSENGER5.put("last_name", " ");
                    PASSENGER5.put("nic", nic_number5);
                    jsonArray.put(PASSENGER5);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }



            }
            else  if(i==6)
            {

                try {

                    PASSENGER6.put("age", age_6);
                    PASSENGER6.put("email", mobileverificationemail);
                    PASSENGER6.put("first_name", full_name6);
                    PASSENGER6.put("gender", gender6);
                    PASSENGER6.put("last_name", " ");
                    PASSENGER6.put("nic", nic_number6);
                    jsonArray.put(PASSENGER6);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


            }


        }






     /* String jsonStr = jsonArray.toString();
        Log.d("gggggg", " " + jsonStr);*/


    }

}