package com.fm.travels.bus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import Adapter.DropingPointAdapter;
import model.BoardingPointModel;

public class Bingobus32Activity extends AppCompatActivity {

    Integer image[] = {R.drawable.marker,R.drawable.marker,R.drawable.marker};
    String city[] ={"Thokar Niaz Baig","Bund road",
            "kalma chowk"};
    String time[] = {"8:00 pm","8:15 pm","8:30 pm"};


    LinearLayout city1,card_bottom,booknow;
    ImageView location1;

    TextView boardingtext;

    private RecyclerView recyclerView;
    private DropingPointAdapter boardingPointAdapter;
    private ArrayList<BoardingPointModel> boardingPointModels;

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingobus32);

        back = (ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Bingobus32Activity.this,Bingobus31Activity.class);
                finish();
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
                startActivity(i);
            }
        });
        card_bottom = findViewById(R.id.card_bottom);
        booknow = findViewById(R.id.booknow);

        booknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(Bingobus32Activity.this,Bingobus_Last_StepActivity.class);
                finish();
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                startActivity(j);
            }
        });

        boardingtext = findViewById(R.id.boardingtext);
        boardingtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Bingobus32Activity.this, Bingobus31Activity.class);
                finish();
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                startActivity(i);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.boardingpontrecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Bingobus32Activity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        boardingPointModels = new ArrayList<>();

        for (int i = 0; i < image.length; i++) {
            BoardingPointModel ab = new BoardingPointModel(image[i],city[i],time[i]);
            boardingPointModels.add(ab);
        }
        boardingPointAdapter = new DropingPointAdapter(Bingobus32Activity.this, boardingPointModels,booknow);

        recyclerView.setAdapter(boardingPointAdapter);

        card_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Bingobus32Activity.this,Bingobus_Last_StepActivity.class);
                finish();
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                startActivity(i);
            }
        });

    }
    @Override
    public void onBackPressed()
    {
        Intent intent=new Intent(Bingobus32Activity.this,Bingobus31Activity.class);
        finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
        startActivity(intent);
        // code here to show dialog
        super.onBackPressed();
        // optional depending on your needs
    }
}