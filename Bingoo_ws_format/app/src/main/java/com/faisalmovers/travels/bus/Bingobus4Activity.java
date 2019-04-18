package com.faisalmovers.travels.bus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Adapter.CitynameAdapter;
import Adapter.CustomerAdapter;
import model.Cities;
import model.Customer;
import util.Url;
import util.Utils;

public class Bingobus4Activity extends Url {

    AutoCompleteTextView autoCompleteTextView;
    CustomerAdapter adapter = null;
    ArrayList<Customer> customers = null;
    int layout;
    ImageView back;
    ListView listview;
    ArrayList<Cities> citynames = new ArrayList<Cities>();
    ArrayList<String> citynames1 = new ArrayList<String>();
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    String responmessage;
    CitynameAdapter citynameAdapter;
    ProgressBar progressBar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingobus4);

        Intent i=getIntent();
        layout = i.getIntExtra("layout",0);
        listview = (ListView) findViewById(R.id.listview);
        progressBar =  findViewById(R.id.progressBar);
        customers = new ArrayList<>();
       /* customers = populateCustomerData(customers);*/


        if (Utils.isNetworkAvailable(getApplicationContext())) {

            sendAndRequestResponse(cityweb);
        } else {
            Utils.showErrorToast(getApplicationContext(), "NETWORK CONNECTION");


        }
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        adapter = new CustomerAdapter(this, customers);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(1);
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });






        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String from = adapterView.getItemAtPosition(i).toString();
                SharedPreferences preferences=getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                Cities cities = citynames.get(i);

                String cityid= cities.getId();
               // Log.d("checkerdata",cityid);



                if(from!=null ) {

                    if(layout==1){
                        editor.putString("from", from);
                        editor.commit();
                        editor.putString("fromcityid", cityid);
                        layout=2;
                        customers.clear();
                        adapter.notifyDataSetChanged();
                        customers = populateCustomerData(customers);
                        adapter.notifyDataSetChanged();
                        autoCompleteTextView.setText("");
                        autoCompleteTextView.setHint("Going where?");
                    }
                    else if(layout==2){
                        editor.putString("to", from);
                        editor.putString("tocityid", cityid);
                        editor.commit();
                        finish();
                    }

                }
            }
        });


        listview.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                String from = parent.getItemAtPosition(position).toString();
                SharedPreferences preferences=getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                Cities cities = citynames.get(position);

                String cityid= cities.getId();
               // Log.d("checkerdata",cityid);

                if(from!=null ) {
                    if(layout==1){
                        editor.putString("from", from);
                        editor.putString("fromcityid", cityid);
                    }

                    if(layout==2){
                        editor.putString("to", from);
                        editor.putString("tocityid", cityid);
                    }
                    editor.commit();
                    finish();
                }

            }
        });






    }




    private ArrayList<Customer> populateCustomerData(final ArrayList<Customer> customers) {

        customers.add(new Customer("Lahore", "", 8, R.drawable.clock));
        customers.add(new Customer("Multan", "", 8, R.drawable.clock));
        customers.add(new Customer("Islamabad", "", 10, R.drawable.clock));
        customers.add(new Customer("Peshawar ", "", 8, R.drawable.clock));
        customers.add(new Customer("Karachi", "", 8, R.drawable.clock));
        return customers;
    }

    private void sendAndRequestResponse(String url) {




        mRequestQueue = Volley.newRequestQueue(this);
        mStringRequest = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Log.d("checkerdata",response);

                try {


                    JSONObject jsonObject = new JSONObject(response);

                    JSONObject jsonObject12= jsonObject.getJSONObject("response");
                    JSONArray jsonArray = jsonObject12.getJSONArray("cities");
                    Log.d("checkjsonArray",jsonArray.length() +"/");

                    for (int i =0; i <jsonArray.length(); i++)
                    {

                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String id = jsonObject1.getString("id");
                        String name  = jsonObject1.getString("name");
                        String city_abbr  = jsonObject1.getString("city_abbr");
                        String country = jsonObject1.getString("country") ;
                        String province = jsonObject1.getString("province") ;
                        String active  = jsonObject1.getString("active");

                        customers.add(new Customer(name, "", 8, R.drawable.clock));
                        Cities cities =  new Cities(id,name,city_abbr,country,province,active);
                        citynames.add(cities);
                        citynames1.add(name);

                    }



     /*              recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                   citynameAdapter = new CitynameAdapter(getApplicationContext(),citynames);
                  // adapter.setClickListener(this);
                   recyclerView.setAdapter(citynameAdapter);*/

                    adapter = new CustomerAdapter(getApplicationContext(), customers);
                    autoCompleteTextView.setAdapter(adapter);
                    autoCompleteTextView.setThreshold(1);

                    final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), R.layout.cityname_row, R.id.tvcityName, citynames1);
                    listview.setAdapter(adapter2);

                    progressBar.setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressBar.setVisibility(View.GONE);
                Utils.showInfoToast(getApplicationContext() ," Data not avaible ");

            }
        });
        mRequestQueue.add(mStringRequest);

    }
}