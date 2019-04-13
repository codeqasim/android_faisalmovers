package com.fm.travels.bus;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Bingobus_Edit_MobileActivity extends AppCompatActivity {
    String  number;
    ImageView back,newtest;
    TextView upadatenumber,update;
    EditText numberr;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingobus_edit_mobile);

        Intent intent = getIntent();
        number = intent.getStringExtra("number");

        upadatenumber = (TextView) findViewById(R.id.upadatenumber);
        numberr =(EditText) findViewById(R.id.numberr);
        update  = (TextView) findViewById(R.id.update);
        back    = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });


        String nametext = getColoredSpanned("A text message with a 6-digit verification code \n was sent to", "#464646");
        String numbruser = getColoredSpanned(number,"#009339");
        upadatenumber.setText(Html.fromHtml(nametext+" "+numbruser));
        numberr.setText(number);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext(), Bingobus_mobileverificationActivity.class);
                intent.putExtra("number",number);
                startActivity(intent);

            }
        });


    }
    private String getColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }
}
