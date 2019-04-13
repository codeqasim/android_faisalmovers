package com.fm.travels.bus;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Bingobus_mobileverificationActivity extends AppCompatActivity {

    TextView numberuser;
    TextView next;
    EditText verficationcode;
    Context  context =this;
    ImageView back;
    LinearLayout edit_no;
    String number;
    EditText code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingobus_mobileverification);

        Intent intent = getIntent();
        next = (TextView) findViewById(R.id.forward);
        edit_no =(LinearLayout) findViewById(R.id.edit_no);
        numberuser = (TextView) findViewById(R.id.usernumber);
        code = (EditText) findViewById(R.id.code);
        back = (ImageView) findViewById(R.id.back);



        if(intent!=null)
        {
            number = intent.getStringExtra("number");
            String nametext = getColoredSpanned("A text message with a 6-digit verification code \n was sent to", "#464646");
            String numbruser = getColoredSpanned(" number : " +number,"#009339");
            numberuser.setText(Html.fromHtml(nametext+" "+numbruser));

        }



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (code.getText().toString().trim().equalsIgnoreCase("")) {
                    code.setError("code");
                } else {
                    Intent i = new Intent(context, Bingobus24Activity.class);
                    context.startActivity(i);
                }

                    //Intent i = new Intent(context, Bingobus24Activity.class);
                //context.startActivity(i);
            }
        });

        edit_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(context, Bingobus_Edit_MobileActivity.class);
                i.putExtra("number",number);
                context.startActivity(i);
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
