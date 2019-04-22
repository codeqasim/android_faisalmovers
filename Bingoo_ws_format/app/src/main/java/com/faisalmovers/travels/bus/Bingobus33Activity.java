package com.faisalmovers.travels.bus;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import model.Bingobus7Model;
import util.MJSONObject;

public class Bingobus33Activity extends AppCompatActivity implements OnSeatSelected{

    private static final int COLUMNS = 5;
    private TextView txtSeatSelected,seatnum;
    int count=0,seatcount=0;
    LinearLayout next;
    LinearLayout detailes,recipt;
    Dialog slideDialog;
    ImageView back_press;
    ImageView cancel;
    private AlertDialog.Builder builder;
    Context context =this;
    Bingobus7Model bingobus7Model;
    int pricetickets =0;
    String price ="0";
    Intent da;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingobus33);



         da = getIntent();
        bingobus7Model = (Bingobus7Model)da.getSerializableExtra("sampleObject");
        price = bingobus7Model.getPrice();
        pricetickets = Integer.parseInt(price);



        txtSeatSelected = (TextView)findViewById(R.id.txt_seat_selected);
        seatnum = (TextView)findViewById(R.id.seatnum);
        seatnum.setText( "Seats Selected : "+String.valueOf(seatcount));

        back_press = (ImageView) findViewById(R.id.back_press);
        back_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });



        recipt = (LinearLayout)findViewById(R.id.recipt);
        recipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, Bingobus28Activity.class);
                i.putExtra("sampleObject", bingobus7Model);
                context.startActivity(i);

            }
        });




        detailes = (LinearLayout)findViewById(R.id.detailes);
        detailes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, Bingobus27Activity.class);
                i.putExtra("sampleObject", bingobus7Model);
                context.startActivity(i);

            }
        });

        next = (LinearLayout)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String totalamountofseat = txtSeatSelected.getText().toString();
                String numberofseat = seatnum.getText().toString();
                SharedPreferences pref = context.getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("totalamountofseat", totalamountofseat);
                editor.putString("numberofseat", numberofseat);
                editor.commit();
                Intent i = new Intent(context, Bingobus31Activity.class);
                i.putExtra("sampleObject", bingobus7Model);
                context.startActivity(i);

            }
        });


        List<AbstractItem> items = new ArrayList<>();
        for (int i=0; i<50; i++) {

            if (i%COLUMNS==0 || i%COLUMNS==4)
            {

                items.add(new EdgeItem(String.valueOf(i)));
            } else if (i%COLUMNS==1 || i%COLUMNS==3)
            {
                items.add(new CenterItem(String.valueOf(i)));
            } else {
                items.add(new EmptyItem(String.valueOf(i)));
            }
        }

        GridLayoutManager manager = new GridLayoutManager(this, COLUMNS);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lst_items);
        recyclerView.setLayoutManager(manager);

        AirplaneAdapter adapter = new AirplaneAdapter(this, items);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSeatSelected(int x) {
        //txtSeatSelected.setText("Book "+count+" seats");
        if (x==2){
            count+=pricetickets;
            seatcount+=1;
            txtSeatSelected.setText(String.valueOf(count));
            seatnum.setText("Seats Selected : "+String.valueOf(seatcount));
        }
        else if (x==1){
            count-=pricetickets;
            seatcount-=1;
            txtSeatSelected.setText(String.valueOf(count));
            seatnum.setText( "Seats Selected : "+String.valueOf(seatcount));
        }
        else {

        }
    }

    @Override
    public void onBackPressed()
    {
       /* Intent i = new Intent(Bingobus33Activity.this, ListinwsActivity.class);
        finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
        startActivity(i);*/
        // code here to show dialog
        super.onBackPressed();

        finish();
        // optional depending on your needs
    }




    @Override
    protected void onPause() {
        super.onPause();

        bingobus7Model = (Bingobus7Model)da.getSerializableExtra("sampleObject");
        price = bingobus7Model.getPrice();
        pricetickets = Integer.parseInt(price);
        Log.d("lifecycle3","onPause invoked" +price);
    }



}