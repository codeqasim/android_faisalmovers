package com.faisalmovers.travels.bus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import Adapter.BoardingPointAdapter;
import model.Bingobus7Model;
import model.BoardingPointModel;

public class Bingobus31Activity extends AppCompatActivity {

    Integer image[] = {R.drawable.marker,R.drawable.marker,R.drawable.marker};
    String city[] ={"Bund road","kalma chowk ",
    "Thokar Niaz Baig"};
    String time[] = {"8:00 pm","8:15 pm","8:30 pm"};
    Bingobus7Model bingobus7Model;


    private RecyclerView recyclerView;
    private BoardingPointAdapter boardingPointAdapter;
    private ArrayList<BoardingPointModel> boardingPointModels;
    ImageView back;
    TextView droppingtext,totalamount ,totalseatnum ;
    Context context;
    Intent da;

    ArrayList<String> dropingpoint = new ArrayList<>() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingobus31);


         da = getIntent();
        bingobus7Model = (Bingobus7Model)da.getSerializableExtra("sampleObject");





        dropingpoint = bingobus7Model.getBoardingPoints();
        String time  = bingobus7Model.getDepartureTime();








        totalamount = (TextView) findViewById(R.id.total);
        totalseatnum = (TextView) findViewById(R.id.seatnum);
        back= (ImageView)findViewById(R.id.back);


        droppingtext = (TextView) findViewById( R.id.droppingtext);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        totalamount.setText("TOTAL : Rs "+pref.getString("totalamountofseat", null));
        totalseatnum.setText(pref.getString("numberofseat", null));

        recyclerView = (RecyclerView) findViewById(R.id.boardingpontrecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Bingobus31Activity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        boardingPointModels = new ArrayList<>();

        for (int i = 0; i < dropingpoint.size(); i++) {


            String name1 = dropingpoint.get(i);

            BoardingPointModel ab = new BoardingPointModel(image[i],name1,time);
            boardingPointModels.add(ab);
        }
        boardingPointAdapter = new BoardingPointAdapter(Bingobus31Activity.this, boardingPointModels);
        recyclerView.setAdapter(boardingPointAdapter);






    }

    @Override
    public void onBackPressed()
    {
      // super.onBackPressed();  // optional depending on your needs
        // code here to show dialog
        /*Intent intent=new Intent(Bingobus31Activity.this,ListinwsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.left_in, R.anim.right_out);*/
        finish();

    }
    @Override
    protected void onPause() {
        super.onPause();

        bingobus7Model = (Bingobus7Model)da.getSerializableExtra("sampleObject");

        Log.d("lifecycle3","onPause invoked" );
    }
}