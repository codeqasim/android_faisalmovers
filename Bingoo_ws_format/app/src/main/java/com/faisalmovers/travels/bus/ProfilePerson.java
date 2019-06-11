package com.faisalmovers.travels.bus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import model.Bingobus7Model;
import util.Url;
import util.Utils;

public class ProfilePerson extends Url implements View.OnClickListener {

    LinearLayout linear1, linear2, linear3, linear4;
    LinearLayout male,female,bycash,easypisa;
    TextView txtmale,txtfemale,next;
    ImageView bus, discount, checked, user;
    TextView search, offer, booking, profile;
    EditText password,fullname,email,phone,nic;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Gson gson;
    Context context=this ;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    ProgressBar progressBar2;

    Bingobus7Model bingobus7Model;
    Intent da;
    private boolean inProgress = false;
    String idupdate;

    String valuecfm;
    Boolean nectcheckbuuton;
    CardView optionmain;
    TextInputLayout password1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_person);



        if( getIntent().getExtras() != null)
        {
            da = getIntent();
            valuecfm= da.getStringExtra("vlaue");
            bingobus7Model = (Bingobus7Model)da.getSerializableExtra("sampleObject");
            //do here
        }


        Log.d("valuecfmvaluecfm",valuecfm);
        password1 =  findViewById(R.id.password1);
        optionmain = (CardView)  findViewById(R.id.optionmain);
        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar2.setVisibility(View.GONE);
        fullname = (EditText) findViewById(R.id.fullname);
        password=findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        nic = (EditText) findViewById(R.id.nic);
        next =(TextView) findViewById(R.id.next);

        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        editor = pref.edit();
        nectcheckbuuton= pref.getBoolean("Check_Login", false);

        if(nectcheckbuuton ==false)
        {
            nectcheckbuuton=false;
        }else {
           // next.setVisibility(View.GONE);
            next.setText("Update");
            password1.setVisibility(View.GONE);
             idupdate= "1";
        }

        if(valuecfm.equals("3"))
        {
            next.setText("Signup");
            optionmain.setVisibility(View.GONE);
        }

        phone.setHint("");

        String mobileverification = pref.getString("mobileverification",null);
        String mobileverificationemail =pref.getString("email",null);
        String mobileverificationefullname =pref.getString("name",null);
        String mobileverificationenic =pref.getString("nic",null);
        String mobileverificationepassword=pref.getString("password",null);
        String gander =pref.getString("gander",null);

        fullname.setText(mobileverificationefullname);
        phone.setText(mobileverification);
        email.setText(mobileverificationemail);
        nic.setText(mobileverificationenic);
        password.setText(mobileverificationepassword);
       // editor.putString("mobileverification", number);
       // editor.commit();
        password.addTextChangedListener(new TextWatcher() {

            // the user's changes are saved here
            public void onTextChanged(CharSequence c, int start, int before, int count) {

            }

            public void beforeTextChanged(CharSequence c, int start, int count, int after) {
                // this space intentionally left blank
            }

            public void afterTextChanged(Editable c) {

                String password1 = String.valueOf(password.getText());
                editor.putString("mobileverificationepassword", password1);
                editor.commit();
                // this one too
            }
        });

        email.addTextChangedListener(new TextWatcher() {

            // the user's changes are saved here
            public void onTextChanged(CharSequence c, int start, int before, int count) {

            }

            public void beforeTextChanged(CharSequence c, int start, int count, int after) {
                // this space intentionally left blank
            }

            public void afterTextChanged(Editable c) {

                 String textemail = String.valueOf(email.getText());
                editor.putString("mobileverificationemail", textemail);
                editor.commit();
                // this one too
            }
        });



        phone.addTextChangedListener(new TextWatcher() {

            // the user's changes are saved here
            public void onTextChanged(CharSequence c, int start, int before, int count) {

            }

            public void beforeTextChanged(CharSequence c, int start, int count, int after) {
                // this space intentionally left blank
            }

            public void afterTextChanged(Editable c) {

                String textemail = String.valueOf(phone.getText());
                editor.putString("mobileverification", textemail);
                editor.commit();
                // this one too
            }
        });


        fullname.addTextChangedListener(new TextWatcher() {

            // the user's changes are saved here
            public void onTextChanged(CharSequence c, int start, int before, int count) {

            }

            public void beforeTextChanged(CharSequence c, int start, int count, int after) {
                // this space intentionally left blank
            }

            public void afterTextChanged(Editable c) {

                String textemail = String.valueOf(fullname.getText());
                editor.putString("mobileverificationfullname", textemail);
                editor.commit();
                // this one too
            }
        });



        nic.addTextChangedListener(new TextWatcher() {

            // the user's changes are saved here
            public void onTextChanged(CharSequence c, int start, int before, int count) {

            }

            public void beforeTextChanged(CharSequence c, int start, int count, int after) {
                // this space intentionally left blank
            }

            public void afterTextChanged(Editable c) {

                String textemail = String.valueOf(nic.getText());
                editor.putString("mobileverificationnic", textemail);
                editor.commit();
                // this one too
            }
        });







        //        custome bottombar
        linear1 = (LinearLayout) findViewById(R.id.liner1);
        linear2 = (LinearLayout) findViewById(R.id.liner2);
        linear3 = (LinearLayout) findViewById(R.id.liner3);
        linear4 = (LinearLayout) findViewById(R.id.liner4);

        male = (LinearLayout)findViewById(R.id.male);
        female = (LinearLayout)findViewById(R.id.female);
        txtmale = (TextView) findViewById(R.id.txtmale);
        txtfemale = (TextView) findViewById(R.id.txtfemale);









        bus = (ImageView) findViewById(R.id.bus);
        discount = (ImageView) findViewById(R.id.discount);
        checked = (ImageView) findViewById(R.id.checked);
        user = (ImageView) findViewById(R.id.user);

        search = (TextView) findViewById(R.id.search);
        offer = (TextView) findViewById(R.id.offer);
        booking = (TextView) findViewById(R.id.booking);
        profile = (TextView) findViewById(R.id.profile);

        linear1.setOnClickListener(this);
        linear2.setOnClickListener(this);
        linear3.setOnClickListener(this);
        linear4.setOnClickListener(this);
        male.setOnClickListener(this);
        female.setOnClickListener(this);
        next.setOnClickListener(this);





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




        bus.setImageResource(R.drawable.ic_bus_gray);
        discount.setImageResource(R.drawable.ic_percentage_gray);
        checked.setImageResource(R.drawable.ic_bookings_gray);
        user.setImageResource(R.drawable.ic_profile_blue);

        search.setTextColor(Color.parseColor("#91959d"));
        offer.setTextColor(Color.parseColor("#91959d"));
        booking.setTextColor(Color.parseColor("#91959d"));
        profile.setTextColor(Color.parseColor("#000000"));


    }


    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.liner1:
                bus.setImageResource(R.drawable.ic_bus_gray);
                discount.setImageResource(R.drawable.ic_percentage_gray);
                checked.setImageResource(R.drawable.ic_bookings_gray);
                user.setImageResource(R.drawable.ic_profile_gray);

                search.setTextColor(Color.parseColor("#000000"));
                offer.setTextColor(Color.parseColor("#91959d"));
                booking.setTextColor(Color.parseColor("#91959d"));
                profile.setTextColor(Color.parseColor("#91959d"));

                Intent i = new Intent(context, Bingobus24Activity.class);
                context.startActivity(i);
                break;

            case R.id.liner2:
                bus.setImageResource(R.drawable.ic_bus_gray);
                discount.setImageResource(R.drawable.ic_percentage_blue);
                checked.setImageResource(R.drawable.ic_bookings_gray);
                user.setImageResource(R.drawable.ic_profile_gray);

                search.setTextColor(Color.parseColor("#91959d"));
                offer.setTextColor(Color.parseColor("#000000"));
                booking.setTextColor(Color.parseColor("#91959d"));
                profile.setTextColor(Color.parseColor("#91959d"));


                Intent intent = new Intent(getApplicationContext(),Offers.class);
                startActivity(intent);


                break;

            case R.id.liner3:
                bus.setImageResource(R.drawable.ic_bus_gray);
                discount.setImageResource(R.drawable.ic_percentage_gray);
                checked.setImageResource(R.drawable.ic_bookings_blue);
                user.setImageResource(R.drawable.ic_profile_gray);

                search.setTextColor(Color.parseColor("#91959d"));
                offer.setTextColor(Color.parseColor("#91959d"));
                booking.setTextColor(Color.parseColor("#000000"));
                profile.setTextColor(Color.parseColor("#91959d"));

                Intent home = new Intent(context, BingoBusBookingHistoryActivity.class);
                context.startActivity(home);
                break;

            case R.id.liner4:
                bus.setImageResource(R.drawable.ic_bus_gray);
                discount.setImageResource(R.drawable.ic_percentage_gray);
                checked.setImageResource(R.drawable.ic_bookings_gray);
                user.setImageResource(R.drawable.ic_profile_blue);

                search.setTextColor(Color.parseColor("#91959d"));
                offer.setTextColor(Color.parseColor("#91959d"));
                booking.setTextColor(Color.parseColor("#91959d"));
                profile.setTextColor(Color.parseColor("#000000"));
                break;

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

            case R.id.next:

                if (idupdate =="1")
                {
                    update_user_detail(updateprofiledata);

                }else
                {

                    signup(signupurl);
                }

                break;


        }
    }
    @Override
    public void onBackPressed() {

        // dailogbox();
        finish();

    }

    public  void signup(String url)
    {

        if (!validate()) {
            onSignupFailed();
            return;
        }else
        {

            creatuser(url);
        }

        //fullnameemailphonenic
    }


    public boolean validate() {
        boolean valid = true;

        //fullnameemailphonenic
        String name = fullname.getText().toString();
        String email1 = email.getText().toString();
        String phone_number=phone.getText().toString();
        String nic = this.nic.getText().toString();
        String passwordtext = password.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            fullname.setError("at least 3 characters");
            valid = false;
        } else {
            fullname.setError(null);
        }

        if (passwordtext.isEmpty() || passwordtext.length() < 6) {
            password.setError("at least 6 characters");
            valid = false;
        } else {
            password.setError(null);
        }

        if (nic.isEmpty() || nic.length() < 13) {
            this.nic.setError("at least 13 characters");
            valid = false;
        } else {
            this.nic.setError(null);
        }

        if (email1.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email1).matches()) {
            email.setError("enter a valid email address");
            valid = false;
        } else {
            email.setError(null);
        }

        if (phone_number.isEmpty() ) {
            phone.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            phone.setError(null);
        }

        return valid;
    }

    public void onSignupSuccess() {
        next.setEnabled(true);
       // getActivity().finish();
    }

    public void onSignupFailed() {
       // Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();
        Utils.showErrorToast(getApplicationContext(), "enter complete info first");
        next.setEnabled(true);
    }


    public void update_user_detail(String url)
    {

        progressBar2.setVisibility(View.VISIBLE);
        if (!validate()) {
            onSignupFailed();
            return;
        }

        mRequestQueue = Volley.newRequestQueue(this);
        mStringRequest  = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Log.d("bordingpoint2","response/" +response);
                progressBar2.setVisibility(View.GONE);
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    if(jsonObject.getBoolean("response"))
                    {
                     //   Toast.makeText(getContext(), "Update Successfully", Toast.LENGTH_SHORT).show();

                        Utils.showSuccesToast(getApplicationContext(),"Update Successfully");
                    }else
                    {
                        Utils.showErrorToast(getApplicationContext(), jsonObject.getJSONObject("error").getString("msg"));
                      //  Toast.makeText(getContext(), jsonObject.getJSONObject("error").getString("msg"), Toast.LENGTH_SHORT).show();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("bordingpoint2","error/" +error);
                Utils.showErrorToast(getApplicationContext(),"server issue");
                progressBar2.setVisibility(View.GONE);
                //This code is executed if there is an error.
            }
        }) {
            @Override
            protected Map<String, String> getParams()  {

                String name = fullname.getText().toString();
                String email1 = email.getText().toString();
                String phone_number=phone.getText().toString();
                String nic1 = nic.getText().toString();
                String passwordtext = password.getText().toString();
                Map<String, String> params = new HashMap<String, String>();
                params.put("firstname",name );
                params.put("id", pref.getString("id",""));
                params.put("lastname", " ");
                params.put("city", "111");
                params.put("country", "Pakistan");
                params.put("address1", "111");
                params.put("address2", "11");
                params.put("phone", phone_number);
                params.put("zip", "1111");
                params.put("email", email1);
                params.put("state", "111");
                params.put("password", pref.getString("password",""));

                return params;
            }
        };

        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(mStringRequest);
    }




    @Override
    protected void onResume() {
        super.onResume();
        bus.setImageResource(R.drawable.ic_bus_gray);
        discount.setImageResource(R.drawable.ic_percentage_gray);
        checked.setImageResource(R.drawable.ic_bookings_gray);
        user.setImageResource(R.drawable.ic_profile_blue);

        search.setTextColor(Color.parseColor("#91959d"));
        offer.setTextColor(Color.parseColor("#91959d"));
        booking.setTextColor(Color.parseColor("#91959d"));
        profile.setTextColor(Color.parseColor("#000000"));
    }


    public void creatuser(String url)
    {
        progressBar2.setVisibility(View.VISIBLE);
        inProgress=true;
        mRequestQueue = Volley.newRequestQueue(this);
        mStringRequest  = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Log.d("bordingpoint2","response/" +response);

                try {


                    JSONObject main_json = new JSONObject(response);
                    JSONObject responseObj = main_json.getJSONObject("response");
                    if(!main_json.getJSONObject("error").getBoolean("status"))
                    {
                        editor.putBoolean("Check_Login",true);
                        editor.putString("email",responseObj.getString("email"));
                        editor.putString("name",responseObj.getString("fullname"));
                        editor.putString("password",responseObj.getString("password"));
                        editor.putString("nic",responseObj.getString("nic"));
                        editor.putString("id",responseObj.getString("id"));
                        editor.commit();

                        Utils.showSuccesToast(getApplicationContext(),"Registration Successful");
                        progressBar2.setVisibility(View.GONE);

                        if (valuecfm.equals("2") )
                        {
                            Intent i = new Intent(context, Bingobus_Last_StepActivity.class);
                            i.putExtra("sampleObject", bingobus7Model);
                            context.startActivity(i);
                        }
                        if (valuecfm.equals("3"))
                        {
                            Intent intent = new Intent(context, Bingobus24Activity.class);
                            finish();
                            startActivity(intent);
                        }


                        next.setText("Update");
                    }
                    else
                    {
                        JSONObject error_object=main_json.getJSONObject("error");
                        Toast.makeText(getApplicationContext(),error_object.getString("msg"),Toast.LENGTH_LONG).show();
                        Utils.showErrorToast(getApplicationContext(),error_object.getString("msg"));
                    }

                  //  inProgress=false;
                } catch (JSONException e) {
                    //    progressDialog.dismiss();

                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    progressBar2.setVisibility(View.GONE);
                    e.printStackTrace();
                  //  inProgress=false;
                }


            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("bordingpoint2","error/" +error);
                Utils.showErrorToast(getApplicationContext(),"server issue");
                progressBar2.setVisibility(View.GONE);
                //This code is executed if there is an error.
                //inProgress=false;
            }
        }) {
            @Override
            protected Map<String, String> getParams()  {

                String name = fullname.getText().toString();
                String email1 = email.getText().toString();
                String phone_number=phone.getText().toString();
                String nic1 = nic.getText().toString();
                String passwordtext = password.getText().toString();
                Map<String, String> params = new HashMap<String, String>();
                params.put("email",email1);
                params.put("password",passwordtext);
                params.put("first_name",name);
                params.put("last_name"," ");
                params.put("phone",phone_number);
                params.put("nic",nic1);
                return params;
            }
        };

        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(mStringRequest);
    }
}
