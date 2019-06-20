package com.faisalmovers.travels.bus;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapter.CustomSpinnerAdapter;
import model.Country;
import util.Url;
import util.Utils;

public class Bingobus_Select_CountryActivity extends Url {

    private Spinner spinner;
    private CustomSpinnerAdapter spinnerAdapter;
    private List<Country> countries = new ArrayList<Country>();
    TextView next,signup,forget;
    ImageView back;
    Animation animation;
    Context context =this;
    EditText _passwordText,_emailText;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    ProgressBar progressBar2;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Gson gson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingobus_select_country);


        forget =  findViewById(R.id.forget);
        next =  findViewById(R.id.next);
        signup = findViewById(R.id.signup);
        _emailText = findViewById(R.id.email);
        _passwordText = findViewById(R.id.password);
        progressBar2 = findViewById(R.id.progressBar2);
        progressBar2.setVisibility(View.GONE);
        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        editor = pref.edit();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!validate()) {
                    //Utils.showErrorToast(getApplicationContext(),"server issue");
                    return;
                }else
                {
                    String emaill= String.valueOf(_emailText.getText());
                    String pswword= String.valueOf(_passwordText.getText());
                    loginuser(login,emaill,pswword);
                }

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent i = new Intent(Bingobus_Select_CountryActivity.this, Bingobus_walkthruoghActivity.class);
                startActivity(i);
               // finish();
               /* Intent Profile = new Intent(context,ProfilePerson.class);
                Profile.putExtra("vlaue", "3");
                context.startActivity(Profile);*/
            }
        });


        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent i = new Intent(Bingobus_Select_CountryActivity.this, Forgotpassword.class);
                startActivity(i);
                // finish();
               /* Intent Profile = new Intent(context,ProfilePerson.class);
                Profile.putExtra("vlaue", "3");
                context.startActivity(Profile);*/
            }
        });


    }
    @Override
    public void onBackPressed() {
        // Simply Do noting!

       dailogbox();
    }

    @SuppressLint("ResourceAsColor")
    public  void dailogbox ()
    {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
        alertDialogBuilder.setMessage("Are you sure, You wanted to Exit");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        //  Toast.makeText(getApplicationContext(),"You clicked yes button",Toast.LENGTH_LONG).show();
                        finish();
                        finishAffinity();
                        System.exit(0);

                    }
                });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                //  DialogInterface.dismiss();

                dialog.cancel();
            }
        });


        alertDialogBuilder.show();

        // alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(R.color.fm);
        //alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(R.color.fm);



    }


    public void loginuser(String url, final String email1, final String password)
    {
        progressBar2.setVisibility(View.VISIBLE);
        mRequestQueue = Volley.newRequestQueue(this);
        mStringRequest  = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("eenenenen",response.toString());
                try {

                    JSONObject main_json = new JSONObject(response);
                    if(main_json.getBoolean("response"))
                    {
                        JSONObject userInfo_object=main_json.getJSONObject("userInfo");
                        editor.putBoolean("Check_Login",true);
                        editor.putString("email",userInfo_object.getString("email"));
                        editor.putString("name",userInfo_object.getString("firstName")+" "+userInfo_object.getString("lastName"));
                        editor.putString("password",password);
                        editor.putString("mobileverification",userInfo_object.getString("mobile"));
                        editor.putString("nic",userInfo_object.getString("nic"));
                        editor.putString("id",userInfo_object.getString("id"));
                        editor.commit();

                        Utils.showSuccesToast(getApplicationContext(),"login Successfull");
                        Intent i = new Intent(Bingobus_Select_CountryActivity.this, Bingobus24Activity.class);
                        startActivity(i);
                        finish();
                        progressBar2.setVisibility(View.GONE);
                    }
                    else
                    {
                        JSONObject error_object=main_json.getJSONObject("error");
                        //Toast.makeText(getContext(),error_object.getString("msg"),Toast.LENGTH_LONG).show();
                        Utils.showInfoToast(getApplicationContext(),error_object.getString("msg"));
                        progressBar2.setVisibility(View.GONE);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    progressBar2.setVisibility(View.GONE);
                    //Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    Utils.showErrorToast(getApplicationContext(),"server issue");
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


                Map<String, String> params = new HashMap<String, String>();
                params.put("email",email1);
                params.put("password",password);
                return params;
            }
        };

        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(mStringRequest);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 ) {
            _passwordText.setError("between 4   characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}