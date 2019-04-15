package com.fm.travels.bus;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import util.Url;
import util.Utils;

public class Bingobus_mobileverificationActivity extends AppCompatActivity {

    TextView numberuser;
    TextView next;
    EditText verficationcode;
    Context  context =this;
    ImageView back;
    LinearLayout edit_no;
    String number;
    EditText code;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    String responmessage;
    Url url;
    String  time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingobus_mobileverification);

        Intent intent = getIntent();
        next = (TextView) findViewById(R.id.forward);
        edit_no =(LinearLayout) findViewById(R.id.edit_no);
        numberuser = (TextView) findViewById(R.id.usernumber);
        code = (EditText) findViewById(R.id.code);
        back = (ImageView) findViewById(R.id.back);



        if(intent!=null)
        {
            number = intent.getStringExtra("number");
            String nametext = getColoredSpanned("A text message with a 6-digit verification code \n was sent to", "#464646");
            String numbruser = getColoredSpanned(" number : " +number,"#009339");
            numberuser.setText(Html.fromHtml(nametext+" "+numbruser));

        }


        DateFormat dateFormat = new SimpleDateFormat("HHmmss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        url = new Url();

         time = dateFormat.format(date);
       // Log.d("checkerdatatime",time);

        String weblink = url.url1+number+"&message="+time+"&devices=2";
       sendAndRequestResponse(weblink);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  Toast.makeText(getApplicationContext(),responmessage+"/",Toast.LENGTH_LONG).show();
                if (code.getText().toString().trim().equalsIgnoreCase("")) {
                    code.setError("code");
                } else {


                    if (code.getText().toString().trim().equals(time)) {
                        //code.setError("code");
                        Intent i = new Intent(context, Bingobus24Activity.class);
                        context.startActivity(i);
                        Utils.showSuccesToast(getApplicationContext(),"successful login");

                    } else {

                        Utils.showErrorToast(getApplicationContext()," wrong ");
                        // Toast.makeText(getApplicationContext(),responmessage+"/ nooo",Toast.LENGTH_LONG).show();
                    }

                }

                    //Intent i = new Intent(context, Bingobus24Activity.class);
                //context.startActivity(i);
            }
        });

        edit_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(context, Bingobus_Edit_MobileActivity.class);
                i.putExtra("number",number);
                context.startActivity(i);
            }
        });


    }
    private String getColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }

    private void sendAndRequestResponse(String url) {




        mRequestQueue = Volley.newRequestQueue(this);
        mStringRequest = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Log.d("checkerdata",response);

               try {


                    JSONObject jsonObject = new JSONObject(response);

                    JSONObject data = jsonObject.optJSONObject("data");
                    JSONArray jsonArray = data.getJSONArray("messages");
                  // Log.d("checkerdata",response +"----" +jsonArray.length());

                   for (int i =0; i <jsonArray.length(); i++)
                   {
                       JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                       String message = jsonObject1.getString("message");
                       responmessage = message;


                   }




               } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        mRequestQueue.add(mStringRequest);

    }
}
