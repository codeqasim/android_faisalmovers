package com.faisalmovers.travels.bus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import util.Url;
import util.Utils;

public class Forgotpassword extends Url {


    ProgressBar progressBar2;
    ImageView back;
    TextView submit;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
   // String email1;
    EditText _emailText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);


        _emailText= (EditText) findViewById(R.id.email);
        back = findViewById(R.id.backscreenid);
        submit = findViewById(R.id.submit);
        progressBar2 = findViewById(R.id.progressBar2);
        progressBar2.setVisibility(View.GONE);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Toast.makeText(getApplicationContext(),"sdafdfadfd",Toast.LENGTH_LONG).show();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  Toast.makeText(getApplicationContext(),"sdafdfadfd",Toast.LENGTH_LONG).show();


                if (!validate()) {
              //      Utils.showErrorToast(getApplicationContext(),"server issue");
                    return;
                }else
                {
                    String emaill= String.valueOf(_emailText.getText());

                    loginuser(forgetpassowrd,emaill);
                    //oginuser(login,emaill,pswword);
                }





            }
        });


    }



    public void loginuser(String url, final String email1)
    {
        progressBar2.setVisibility(View.VISIBLE);
        mRequestQueue = Volley.newRequestQueue(this);
        mStringRequest  = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("eenenenen",response.toString());
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject response1= jsonObject.getJSONObject("response");

                       String  sms = response1.getString("message");
                       Utils.showInfoToast(getApplicationContext(),sms+"");
                       progressBar2.setVisibility(View.GONE);

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
                return params;
            }
        };

        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(50000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(mStringRequest);
    }



    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        return valid;
    }
}

