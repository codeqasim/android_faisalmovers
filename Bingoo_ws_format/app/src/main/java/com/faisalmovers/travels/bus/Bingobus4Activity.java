package com.faisalmovers.travels.bus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import Adapter.CustomerAdapter;
import model.Customer;

public class Bingobus4Activity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;
    CustomerAdapter adapter = null;
    ArrayList<Customer> customers = null;
    int layout;
    ImageView back;
    ListView listview;
    ArrayList<String> citynames = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingobus4);

        Intent i=getIntent();
        layout = i.getIntExtra("layout",0);
        listview = (ListView) findViewById(R.id.listview);

        customers = new ArrayList<>();
        customers = populateCustomerData(customers);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        adapter = new CustomerAdapter(this, customers);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(1);

        for(int k = 0 ; k<customers.size(); k++)
        {
            Customer customer = customers.get(k);
            String  name  =customer.getFirstName();
            citynames.add(name);
        }

        final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, citynames);
        listview.setAdapter(adapter2);

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

                if(from!=null ) {

                    if(layout==1){
                        editor.putString("from", from);
                        editor.commit();
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

                if(from!=null ) {
                    if(layout==1){
                        editor.putString("from", from);
                    }

                    if(layout==2){
                        editor.putString("to", from);
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
}