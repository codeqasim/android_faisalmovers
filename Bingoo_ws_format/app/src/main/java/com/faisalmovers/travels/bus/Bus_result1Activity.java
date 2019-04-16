package com.faisalmovers.travels.bus;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import java.util.ArrayList;
import Adapter.Bingobus_Result1Adapter;
import model.Bingobus_Result1Model;

public class Bus_result1Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Bingobus_Result1Adapter bingobus_result1Adapter;
    private ArrayList<Bingobus_Result1Model> bingobus_result1Models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_result1);

        recyclerView = (RecyclerView)findViewById(R.id.recycle_busresult);
        recyclerView.setLayoutManager(new LinearLayoutManager(Bus_result1Activity.this));

        recyclerView.setFocusableInTouchMode(false);
        recyclerView.setNestedScrollingEnabled(false);
        runAnimation(recyclerView);
    }

    private void runAnimation(RecyclerView recyclerview) {
        Context context = recyclerview.getContext();
        LayoutAnimationController controller = null;

        bingobus_result1Models = new ArrayList<>();

        bingobus_result1Models.add(new Bingobus_Result1Model("A/C Sleeper (2+1)"));
        bingobus_result1Models.add(new Bingobus_Result1Model("A/C Sleeper (2+1)"));
        bingobus_result1Models.add(new Bingobus_Result1Model("A/C Sleeper (2+1)"));
        bingobus_result1Models.add(new Bingobus_Result1Model("A/C Sleeper (2+1)"));
        bingobus_result1Models.add(new Bingobus_Result1Model("A/C Sleeper (2+1)"));
        bingobus_result1Models.add(new Bingobus_Result1Model("A/C Sleeper (2+1)"));
        bingobus_result1Models.add(new Bingobus_Result1Model("A/C Sleeper (2+1)"));

        controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_fall_down);

        bingobus_result1Adapter = new Bingobus_Result1Adapter(Bus_result1Activity.this, bingobus_result1Models);
        recyclerview.setAdapter(bingobus_result1Adapter);

        recyclerview.setLayoutAnimation(controller);
        recyclerview.getAdapter().notifyDataSetChanged();
        recyclerview.scheduleLayoutAnimation();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Bus_result1Activity.this, ListinwsActivity.class);
        finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
        startActivity(i);
    }
}