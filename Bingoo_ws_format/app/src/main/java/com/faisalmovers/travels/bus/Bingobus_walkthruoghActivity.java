package com.faisalmovers.travels.bus;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import util.Utils;

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

                if (Utils.isNetworkAvailable(getApplicationContext())) {

                    if (editextnumber.getText().toString().trim().equalsIgnoreCase("")) {
                        editextnumber.setError("Number");
                    } else {



                        String  number = editextnumber.getText().toString();
                        Intent i = new Intent(context,Bingobus_mobileverificationActivity.class);
                        i.putExtra("number",number);
                        context.startActivity(i);
                    }
                } else {
                    Utils.showErrorToast(getApplicationContext(), "NETWORK CONNECTION");


                }


               
            }
        });


    }


    public void onBackPressed(){
      //  super.onBackPressed();
        finish();
      //  dailogbox();
    }

    @SuppressLint("ResourceAsColor")
    public  void dailogbox ()
    {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
        alertDialogBuilder.setMessage("Are you sure, You wanted to Exit");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        //  Toast.makeText(getApplicationContext(),"You clicked yes button",Toast.LENGTH_LONG).show();
                        finish();
                        finishAffinity();
                        System.exit(0);

                    }
                });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                //  DialogInterface.dismiss();

                dialog.cancel();
            }
        });


        alertDialogBuilder.show();

        // alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(R.color.fm);
        //alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(R.color.fm);



    }
}