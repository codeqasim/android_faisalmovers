package com.faisalmovers.travels.bus;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class Bingobus33Activity extends AppCompatActivity implements OnSeatSelected{

    private static final int COLUMNS = 5;
    private TextView txtSeatSelected;
    int count=0;
    LinearLayout next;
    LinearLayout detailes,recipt;
    Dialog slideDialog;
    ImageView back_press;
    ImageView cancel;
    private AlertDialog.Builder builder;
    Context context =this;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingobus33);

        txtSeatSelected = (TextView)findViewById(R.id.txt_seat_selected);

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
                context.startActivity(i);

            }
        });




        detailes = (LinearLayout)findViewById(R.id.detailes);
        detailes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(context, Bingobus27Activity.class);
                context.startActivity(i);

            }
        });

        next = (LinearLayout)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(context, Bingobus31Activity.class);
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
            count+=720;
            txtSeatSelected.setText(String.valueOf(count));
        }
        else if (x==1){
            count-=720;
            txtSeatSelected.setText(String.valueOf(count));
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

}