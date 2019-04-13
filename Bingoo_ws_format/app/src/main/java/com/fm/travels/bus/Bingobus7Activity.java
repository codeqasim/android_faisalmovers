package com.fm.travels.bus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import Adapter.Bingobus7Adapter;
import model.Bingobus7Model;

public class Bingobus7Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Bingobus7Adapter bingobas7_adapter;
    private ArrayList<Bingobus7Model> bingobas7_models;
    TextView up,down;
    ImageView back;
    Context context=this;
     Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingobus7);

        back = (ImageView)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Bingobus7Activity.this,Bingobus24Activity.class);
                finish();
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
                startActivity(i);
            }
        });

        up = findViewById(R.id.up);
        down = findViewById(R.id.down);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        up.setText(pref.getString("from",null));
        down.setText(pref.getString("to",null));

        recyclerView = (RecyclerView)findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(new LinearLayoutManager(Bingobus7Activity.this));
        recyclerView.setFocusableInTouchMode(false);
        recyclerView.setNestedScrollingEnabled(false);
//        animation content slowly updated in view for using this code
        runAnimation(recyclerView);


      /*  recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(context,
                recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        Intent i = new Intent(context, Bingobus33Activity.class);
                        context.startActivity(i);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );*/

    }
    private void runAnimation(RecyclerView recyclerview) {
        Context context = recyclerview.getContext();
        LayoutAnimationController controller = null;

        bingobas7_models = new ArrayList<>();

        bingobas7_models.add(new Bingobus7Model("A/C Sleeper (2+1)"));
        bingobas7_models.add(new Bingobus7Model("A/C Sleeper (2+1)"));
        bingobas7_models.add(new Bingobus7Model("A/C Sleeper (2+1)"));
        bingobas7_models.add(new Bingobus7Model("A/C Sleeper (2+1)"));
        bingobas7_models.add(new Bingobus7Model("A/C Sleeper (2+1)"));
        bingobas7_models.add(new Bingobus7Model("A/C Sleeper (2+1)"));
        bingobas7_models.add(new Bingobus7Model("A/C Sleeper (2+1)"));

        controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fall_down);

        bingobas7_adapter = new Bingobus7Adapter(Bingobus7Activity.this, bingobas7_models);
        recyclerview.setAdapter(bingobas7_adapter);

        recyclerview.setLayoutAnimation(controller);
        recyclerview.getAdapter().notifyDataSetChanged();
        recyclerview.scheduleLayoutAnimation();


    }

    @Override
    public void onBackPressed()
    {
        // code here to show dialog
        super.onBackPressed();
        finish();
        /*Intent i = new Intent(Bingobus7Activity.this,ListinwsActivity.class);
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
       startActivity(i);*/
    }
}