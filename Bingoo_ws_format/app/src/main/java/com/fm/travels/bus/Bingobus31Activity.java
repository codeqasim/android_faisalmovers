package com.fm.travels.bus;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import Adapter.BoardingPointAdapter;
import model.BoardingPointModel;

public class Bingobus31Activity extends AppCompatActivity {

    Integer image[] = {R.drawable.marker,R.drawable.marker,R.drawable.marker};
    String city[] ={"Bund road","kalma chowk ",
    "Thokar Niaz Baig"};
    String time[] = {"8:00 pm","8:15 pm","8:30 pm"};


    private RecyclerView recyclerView;
    private BoardingPointAdapter boardingPointAdapter;
    private ArrayList<BoardingPointModel> boardingPointModels;
    ImageView back;
    TextView droppingtext ;
    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingobus31);

        back= (ImageView)findViewById(R.id.back);

        droppingtext = (TextView) findViewById( R.id.droppingtext);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Bingobus31Activity.this,Bingobus33Activity.class);
                finish();
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
                startActivity(i);
            }
        });



        recyclerView = (RecyclerView) findViewById(R.id.boardingpontrecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Bingobus31Activity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        boardingPointModels = new ArrayList<>();

        for (int i = 0; i < image.length; i++) {
            BoardingPointModel ab = new BoardingPointModel(image[i],city[i],time[i]);
            boardingPointModels.add(ab);
        }
        boardingPointAdapter = new BoardingPointAdapter(Bingobus31Activity.this, boardingPointModels);
        recyclerView.setAdapter(boardingPointAdapter);






    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();  // optional depending on your needs
        // code here to show dialog
        /*Intent intent=new Intent(Bingobus31Activity.this,ListinwsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.left_in, R.anim.right_out);*/
        finish();

    }
}