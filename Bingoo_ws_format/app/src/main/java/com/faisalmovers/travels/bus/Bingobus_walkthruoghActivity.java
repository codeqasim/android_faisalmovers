package com.faisalmovers.travels.bus;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Bingobus_walkthruoghActivity extends AppCompatActivity {

    Context context =this;
    ImageView back;
    EditText editextnumber;
    TextView next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingobus_walkthruogh);

        back = (ImageView) findViewById( R.id.back);
        editextnumber = (EditText) findViewById( R.id.editext);
        next = (TextView) findViewById(R.id.next);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editextnumber.getText().toString().trim().equalsIgnoreCase("")) {
                    editextnumber.setError("Number");
                } else {



                    String  number = editextnumber.getText().toString();
                    Intent i = new Intent(context,Bingobus_mobileverificationActivity.class);
                    i.putExtra("number",number);
                    context.startActivity(i);
                }
               
            }
        });


    }


    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }
}