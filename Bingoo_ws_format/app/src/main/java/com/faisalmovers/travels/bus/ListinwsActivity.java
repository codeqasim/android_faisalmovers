package com.faisalmovers.travels.bus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import Adapter.ListAdapter;
import model.ListModel;

public class ListinwsActivity extends AppCompatActivity {

    String list[] = {"1.Splash_screenActivity","2.Bingobus_Select_CountryActivity",
            "3.Bingobus_walkthruoghActivity","4.Bingobus_mobileverificationActivity",
            "5.Bingobus_Edit_MobileActivity","6.Bingobus24Activity",
            "7.Bingobus7Activity","8.Bus_result1Activity","9.Bingobus33Activity",
            "10.Bingobus27Activity","11.Bingobus28Activity","12.Bingobus31Activity",
            "13.Bingobus32Activity","14.Bingobus_Last_StepActivity","15.BingoBusBookingDetailActivity",
    "16.BingoBusYourTicketBookedActivity","17.BingoBusBookingHistoryActivity"};

    private RecyclerView recyclerView;
    private ListAdapter listAdapter;
    private ArrayList<ListModel> listModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listinws);

        recyclerView = (RecyclerView)findViewById(R.id.List_recycle);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListinwsActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        listModels = new ArrayList<>();

        for (int i = 0; i < list.length; i++) {
            ListModel ab = new ListModel(list[i]);
            listModels.add(ab);
        }
        listAdapter = new ListAdapter(ListinwsActivity.this, listModels);
        recyclerView.setAdapter(listAdapter);
    }
}
