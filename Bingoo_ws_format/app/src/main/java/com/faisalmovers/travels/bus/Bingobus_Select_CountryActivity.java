package com.faisalmovers.travels.bus;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import Adapter.CustomSpinnerAdapter;
import model.Country;

public class Bingobus_Select_CountryActivity extends AppCompatActivity {

    private Spinner spinner;
    private CustomSpinnerAdapter spinnerAdapter;
    private List<Country> countries = new ArrayList<Country>();
    TextView next,signup;
    ImageView back;
    Animation animation;
    Context context =this;
    EditText password,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingobus_select_country);


        next =  findViewById(R.id.next);
        signup = findViewById(R.id.signup);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Bingobus_Select_CountryActivity.this, Bingobus24Activity.class);
                startActivity(i);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent i = new Intent(Bingobus_Select_CountryActivity.this, Bingobus_walkthruoghActivity.class);
                startActivity(i);
               /* Intent Profile = new Intent(context,ProfilePerson.class);
                Profile.putExtra("vlaue", "3");
                context.startActivity(Profile);*/
            }
        });


    }
    @Override
    public void onBackPressed() {
        // Simply Do noting!

       dailogbox();
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