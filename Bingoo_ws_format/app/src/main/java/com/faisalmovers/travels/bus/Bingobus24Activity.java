package com.faisalmovers.travels.bus;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import Adapter.OffersAdapter;
import model.Bingobus7Model;
import model.OffersModel;
import util.Utils;

import static java.security.AccessController.getContext;

public class Bingobus24Activity extends AppCompatActivity implements View.OnClickListener {

    //city change
    TextView uptext, downtext;
    ImageView swap;
    boolean datecfm=false;
    LinearLayout calender1;
    TextView date1, date2, date3;
    DatePickerDialog.OnDateSetListener dateSetListener;
    private static final String TAG = "DatePicer";

    //returen date code
    LinearLayout calenderreturn;
    TextView date_select1, date_select2, date_select3;
    DatePickerDialog.OnDateSetListener dateSetListener1;
    private static final String TAG1 = "DatePicer1";

    //liner onclick color change
    LinearLayout showdata, showdata1;
    FrameLayout circle1, circle2, circle3, circle4, circle5, circle6;
    TextView day1, day2, day3, day4, day5, day6;

    //card come
    TextView searchbus;

    //offer recyclercode
    Integer poster[] = {R.drawable.banner1, R.drawable.banner2, R.drawable.banner3,
            R.drawable.banner4};

    private RecyclerView recyclerView;
    private OffersAdapter offersAdapter;
    private ArrayList<OffersModel> offersModel;

    //bottombar
    LinearLayout linear1, linear2, linear3, linear4;
    ImageView bus, discount, checked, user;
    TextView search, offer, booking, profile;

    //intent code
    LinearLayout goingfrom, goingto;
    String country1, country2;
    Context context=this;
    public static final String PREFS_NAME = "MyApp_Settings";
    SharedPreferences settings;
    ListView listview;
    SharedPreferences pref;
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingobus24);

        goingfrom = (LinearLayout) findViewById(R.id.goingfrom);
        goingto = (LinearLayout) findViewById(R.id.goingto);
        searchbus = (TextView) findViewById(R.id.searchbus);
        pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        editor = pref.edit();



        searchbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!uptext.getText().equals("") && !downtext.getText().equals("") && datecfm ==true)
                {

                    Intent i = new Intent(context, Bingobus7Activity.class);
                    context.startActivity(i);
                }
                else
                {

                    Utils.showErrorToast(getApplicationContext(),"required Date & Location from & to");
                }

            }
        });


        goingfrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(Bingobus24Activity.this, Bingobus5Activity.class);
                i.putExtra("layout", 1);
                startActivity(i);
            }
        });



        goingto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Bingobus24Activity.this, Bingobus4Activity.class);
                i.putExtra("layout", 2);
                startActivity(i);
            }
        });
        settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("show", "yes");
        editor.commit();




        date1 = findViewById(R.id.date1);
        date2 = findViewById(R.id.date2);
        date3 = findViewById(R.id.date3);

//        city change
        uptext = (TextView) findViewById(R.id.uptext);
        downtext = (TextView) findViewById(R.id.downtext);
        swap = (ImageView) findViewById(R.id.swap);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        uptext.setText(pref.getString("from", null));
        downtext.setText(pref.getString("to", null));


        swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = uptext.getText().toString();
                String s2 = downtext.getText().toString();
                uptext.setText(s2);
                downtext.setText(s1);
            }
        });

//        date selection
        calender1 = findViewById(R.id.calender1);
        calender1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(Bingobus24Activity.this, AlertDialog.THEME_DEVICE_DEFAULT_DARK,  dateSetListener, year, month, day);
               // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Date date = new Date(year, month, dayOfMonth);
                SimpleDateFormat outFormat = new SimpleDateFormat("EEE");
                String day = outFormat.format(date);

                Log.d("dayday1",day);
                datecfm=true;

                SharedPreferences preferences=getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                String finaldate = year+"-"+month+"-"+dayOfMonth;
                editor.putString("selectdate",finaldate);
                editor.commit();
            // Toast.makeText(Bingobus24Activity.this, dayOfMonth +"-" +"-", Toast.LENGTH_SHORT).show();

                // Toast.makeText(Bingobus24Activity.this, dayOfMonth+"/ "+month, Toast.LENGTH_SHORT).show();

                if (day.equals("Sun")) {
                    day1.setText("Sat");
                    day2.setText("Sun");
                    day3.setText("Mon");
                } else if (day.equals("Mon")) {
                    day1.setText("Sun");
                    day2.setText("Mon");
                    day3.setText("Tue");
                } else if (day.equals("Tue")) {
                    day1.setText("Mon");
                    day2.setText("Tue");
                    day3.setText("Wed");
                } else if (day.equals("Wed")) {
                    day1.setText("Tue");
                    day2.setText("Wed");
                    day3.setText("Tur");
                } else if (day.equals("Tur")) {
                    day1.setText("Wed");
                    day2.setText("Tur");
                    day3.setText("Fri");
                } else if (day.equals("Fri")) {
                    day1.setText("Tur");
                    day2.setText("Fri");
                    day3.setText("Sat");
                } else if (day.equals("Sat")) {
                    day1.setText("Fri");
                    day2.setText("Sat");
                    day3.setText("Sun");
                }

                Log.d(TAG, "onDateSet: dd: " + dayOfMonth);
                String date11 = dayOfMonth + "";
                date1.setText(date11);

                int countdate = Integer.valueOf(String.valueOf(dayOfMonth));
                int countdate11 = countdate;
                date1.setText(String.valueOf(countdate11));

                int countdate2 = Integer.valueOf(String.valueOf(dayOfMonth));
                int countdate12 = countdate2 + 1;
                date2.setText(String.valueOf(countdate12));

                int countdate3 = Integer.valueOf(String.valueOf(dayOfMonth));
                int countdate13 = countdate3 + 2;
                date3.setText(String.valueOf(countdate13));
            }
        };

//        returen date code

        calenderreturn = findViewById(R.id.calenderreturn);

        calenderreturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(Bingobus24Activity.this,AlertDialog.THEME_DEVICE_DEFAULT_DARK ,dateSetListener1, year, month, day);
                //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

               /* DatePickerDialog dialog = new DatePickerDialog(Bingobus24Activity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateSetListener1, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();*/
            }
        });

        dateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month ;
                Date date = new Date(year, month, dayOfMonth);
                SimpleDateFormat outFormat = new SimpleDateFormat("EEE");
                String day = outFormat.format(date);


                //Toast.makeText(Bingobus24Activity.this, dayOfMonth +"-" +"-", Toast.LENGTH_SHORT).show();
//
                if (day.equals("Sun")) {
                    day4.setText("Sat");
                    day5.setText("Sun");
                    day6.setText("Mon");
                } else if (day.equals("Mon")) {
                    day4.setText("Sun");
                    day5.setText("Mon");
                    day6.setText("Tue");
                } else if (day.equals("Tue")) {
                    day4.setText("Mon");
                    day5.setText("Tue");
                    day6.setText("Wed");
                } else if (day.equals("Wed")) {
                    day4.setText("Tue");
                    day5.setText("Wed");
                    day6.setText("Tur");
                } else if (day.equals("Tur")) {
                    day4.setText("Wed");
                    day5.setText("Tur");
                    day6.setText("Fri");
                } else if (day.equals("Fri")) {
                    day4.setText("Tur");
                    day5.setText("Fri");
                    day6.setText("Sat");
                } else if (day.equals("Sat")) {
                    day4.setText("Fri");
                    day5.setText("Sat");
                    day6.setText("Sun");
                }

                Log.d(TAG, "onDateSet: dd: " + dayOfMonth);
                String date11 = dayOfMonth + "";
                date_select1.setText(date11);

                int countdate = Integer.valueOf(String.valueOf(dayOfMonth));
                int countdate11 = countdate;
                date_select1.setText(String.valueOf(countdate11));

                int countdate2 = Integer.valueOf(String.valueOf(dayOfMonth));
                int countdate12 = countdate2 + 1;
                date_select2.setText(String.valueOf(countdate12));

                int countdate3 = Integer.valueOf(String.valueOf(dayOfMonth));
                int countdate13 = countdate3 + 2;
                date_select3.setText(String.valueOf(countdate13));
            }
        };

//        offer recyclercode
        recyclerView = (RecyclerView) findViewById(R.id.offers);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(Bingobus24Activity.this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        offersModel = new ArrayList<>();

        for (int i = 0; i < poster.length; i++) {
            OffersModel ab = new OffersModel(poster[i]);
            offersModel.add(ab);
        }
        offersAdapter = new OffersAdapter(Bingobus24Activity.this, offersModel);
        recyclerView.setAdapter(offersAdapter);


        showdata = findViewById(R.id.showdata);
        showdata1 = findViewById(R.id.showdata1);

//calender return visibility
        showdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdata.setVisibility(View.GONE);
                showdata1.setVisibility(View.VISIBLE);
            }
        });

//        date custome on click
        circle1 = (FrameLayout) findViewById(R.id.circle1);
        circle2 = (FrameLayout) findViewById(R.id.circle2);
        circle3 = (FrameLayout) findViewById(R.id.circle3);
        circle4 = (FrameLayout) findViewById(R.id.circle4);
        circle5 = (FrameLayout) findViewById(R.id.circle5);
        circle6 = (FrameLayout) findViewById(R.id.circle6);


        date_select1 = findViewById(R.id.date_select1);
        date_select2 = findViewById(R.id.date_select2);
        date_select3 = findViewById(R.id.date_select3);

        day1 = (TextView) findViewById(R.id.day1);
        day2 = (TextView) findViewById(R.id.day2);
        day3 = (TextView) findViewById(R.id.day3);
        day4 = (TextView) findViewById(R.id.day4);
        day5 = (TextView) findViewById(R.id.day5);
        day6 = (TextView) findViewById(R.id.day6);

        circle1.setOnClickListener(this);
        circle2.setOnClickListener(this);
        circle3.setOnClickListener(this);
        circle4.setOnClickListener(this);
        circle5.setOnClickListener(this);
        circle6.setOnClickListener(this);

//        custome bottombar
        linear1 = (LinearLayout) findViewById(R.id.liner1);
        linear2 = (LinearLayout) findViewById(R.id.liner2);
        linear3 = (LinearLayout) findViewById(R.id.liner3);
        linear4 = (LinearLayout) findViewById(R.id.liner4);

        bus = (ImageView) findViewById(R.id.bus);
        discount = (ImageView) findViewById(R.id.discount);
        checked = (ImageView) findViewById(R.id.checked);
        user = (ImageView) findViewById(R.id.user);

        search = (TextView) findViewById(R.id.search);
        offer = (TextView) findViewById(R.id.offer);
        booking = (TextView) findViewById(R.id.booking);
        profile = (TextView) findViewById(R.id.profile);

        linear1.setOnClickListener(this);
        linear2.setOnClickListener(this);
        linear3.setOnClickListener(this);
        linear4.setOnClickListener(this);


        load();
        //loadhistorydata();
    }

    @Override
    public void onClick(View v) {

        SharedPreferences preferences=getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        SimpleDateFormat currentDate = new SimpleDateFormat("yyyy-MM");
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);
        String finaldate ;

        switch (v.getId()) {
            case R.id.liner1:
                bus.setImageResource(R.drawable.ic_bus_blue);
                discount.setImageResource(R.drawable.ic_percentage_gray);
                checked.setImageResource(R.drawable.ic_bookings_gray);
                user.setImageResource(R.drawable.ic_profile_gray);

                search.setTextColor(Color.parseColor("#000000"));
                offer.setTextColor(Color.parseColor("#91959d"));
                booking.setTextColor(Color.parseColor("#91959d"));
                profile.setTextColor(Color.parseColor("#91959d"));
                break;

            case R.id.liner2:
                bus.setImageResource(R.drawable.ic_bus_gray);
                discount.setImageResource(R.drawable.ic_percentage_blue);
                checked.setImageResource(R.drawable.ic_bookings_gray);
                user.setImageResource(R.drawable.ic_profile_gray);

                search.setTextColor(Color.parseColor("#91959d"));
                offer.setTextColor(Color.parseColor("#000000"));
                booking.setTextColor(Color.parseColor("#91959d"));
                profile.setTextColor(Color.parseColor("#91959d"));


                Intent intent = new Intent(getApplicationContext(),Offers.class);
                startActivity(intent);


                break;

            case R.id.liner3:
                bus.setImageResource(R.drawable.ic_bus_gray);
                discount.setImageResource(R.drawable.ic_percentage_gray);
                checked.setImageResource(R.drawable.ic_bookings_blue);
                user.setImageResource(R.drawable.ic_profile_gray);

                search.setTextColor(Color.parseColor("#91959d"));
                offer.setTextColor(Color.parseColor("#91959d"));
                booking.setTextColor(Color.parseColor("#000000"));
                profile.setTextColor(Color.parseColor("#91959d"));

                Intent i = new Intent(context, BingoBusBookingHistoryActivity.class);
                context.startActivity(i);
                break;

            case R.id.liner4:
                bus.setImageResource(R.drawable.ic_bus_gray);
                discount.setImageResource(R.drawable.ic_percentage_gray);
                checked.setImageResource(R.drawable.ic_bookings_gray);
                user.setImageResource(R.drawable.ic_profile_blue);

                search.setTextColor(Color.parseColor("#91959d"));
                offer.setTextColor(Color.parseColor("#91959d"));
                booking.setTextColor(Color.parseColor("#91959d"));
                profile.setTextColor(Color.parseColor("#000000"));


                Intent Profile = new Intent(context,ProfilePerson.class);
                Profile.putExtra("vlaue", "1");
                context.startActivity(Profile);
                break;

            case R.id.circle1:
                circle1.setBackgroundResource(R.drawable.circle_blue);
                circle2.setBackgroundResource(R.drawable.circle_white);
                circle3.setBackgroundResource(R.drawable.circle_white);




                date1.setTextColor(Color.parseColor("#ffffff"));
                date2.setTextColor(Color.parseColor("#91959d"));
                date3.setTextColor(Color.parseColor("#91959d"));

                day1.setTextColor(Color.parseColor("#ffffff"));
                day2.setTextColor(Color.parseColor("#91959d"));
                day3.setTextColor(Color.parseColor("#91959d"));

                String day1one = day1.getText().toString();
                 finaldate = thisDate+"-"+day1one;
                editor.putString("selectdate",finaldate);
                editor.commit();
                datecfm=true;
                break;

            case R.id.circle2:
                circle1.setBackgroundResource(R.drawable.circle_white);
                circle2.setBackgroundResource(R.drawable.circle_blue);
                circle3.setBackgroundResource(R.drawable.circle_white);

                date1.setTextColor(Color.parseColor("#91959d"));
                date2.setTextColor(Color.parseColor("#ffffff"));
                date3.setTextColor(Color.parseColor("#91959d"));

                day1.setTextColor(Color.parseColor("#91959d"));
                day2.setTextColor(Color.parseColor("#ffffff"));
                day3.setTextColor(Color.parseColor("#91959d"));


                String dayday2 = date2.getText().toString();
                finaldate = thisDate+"-"+dayday2;
                editor.putString("selectdate",finaldate);
                editor.commit();
                datecfm=true;
                break;

            case R.id.circle3:
                circle1.setBackgroundResource(R.drawable.circle_white);
                circle2.setBackgroundResource(R.drawable.circle_white);
                circle3.setBackgroundResource(R.drawable.circle_blue);

                date1.setTextColor(Color.parseColor("#91959d"));
                date2.setTextColor(Color.parseColor("#91959d"));
                date3.setTextColor(Color.parseColor("#ffffff"));


                day1.setTextColor(Color.parseColor("#91959d"));
                day2.setTextColor(Color.parseColor("#91959d"));
                day3.setTextColor(Color.parseColor("#ffffff"));


                String dayday3 = date3.getText().toString();
                finaldate = thisDate+"-"+dayday3;
                editor.putString("selectdate",finaldate);
                editor.commit();
                datecfm=true;

                break;

            case R.id.circle4:
                circle4.setBackgroundResource(R.drawable.circle_blue);
                circle5.setBackgroundResource(R.drawable.circle_white);
                circle6.setBackgroundResource(R.drawable.circle_white);

                date_select1.setTextColor(Color.parseColor("#ffffff"));
                date_select2.setTextColor(Color.parseColor("#91959d"));
                date_select3.setTextColor(Color.parseColor("#91959d"));

                day4.setTextColor(Color.parseColor("#ffffff"));
                day5.setTextColor(Color.parseColor("#91959d"));
                day6.setTextColor(Color.parseColor("#91959d"));
                break;

            case R.id.circle5:
                circle4.setBackgroundResource(R.drawable.circle_white);
                circle5.setBackgroundResource(R.drawable.circle_blue);
                circle6.setBackgroundResource(R.drawable.circle_white);

                date_select1.setTextColor(Color.parseColor("#91959d"));
                date_select2.setTextColor(Color.parseColor("#ffffff"));
                date_select3.setTextColor(Color.parseColor("#91959d"));

                day4.setTextColor(Color.parseColor("#91959d"));
                day5.setTextColor(Color.parseColor("#ffffff"));
                day6.setTextColor(Color.parseColor("#91959d"));
                break;

            case R.id.circle6:
                circle4.setBackgroundResource(R.drawable.circle_white);
                circle5.setBackgroundResource(R.drawable.circle_white);
                circle6.setBackgroundResource(R.drawable.circle_blue);

                date_select1.setTextColor(Color.parseColor("#91959d"));
                date_select2.setTextColor(Color.parseColor("#91959d"));
                date_select3.setTextColor(Color.parseColor("#ffffff"));

                day4.setTextColor(Color.parseColor("#91959d"));
                day5.setTextColor(Color.parseColor("#91959d"));
                day6.setTextColor(Color.parseColor("#ffffff"));
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        uptext.setText(pref.getString("from", null));
        downtext.setText(pref.getString("to", null));

    }

    @Override
    public void onBackPressed() {

        dailogbox2();

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
    public void dailogbox2() {

        AlertDialog.Builder alertDialogBuilder =new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
        alertDialogBuilder
                .setMessage("Are you sure, You wanted to Exit or Signout")
                .setCancelable(false)
                .setPositiveButton("Exit  ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity


                        finish();
                        finishAffinity();
                        System.exit(0);




                    }
                }).setNeutralButton("Signout", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {


                settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                editor = settings.edit();
                editor.putString("show", "no");
                editor.commit();

                finish();
                finishAffinity();
                System.exit(0);




            }
        })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing

                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.getWindow().setBackgroundDrawableResource(R.color.colorPrimary);
        // show it
        alertDialog.show();

    }






    public void load()
    {
        SharedPreferences preferences=getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        SimpleDateFormat currentDate = new SimpleDateFormat("yyyy-MM-dd");
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);
        editor.putString("selectdate",thisDate);
        editor.commit();



        SimpleDateFormat outFormat = new SimpleDateFormat("EEE");
        String day = outFormat.format(todayDate);
        if (day.equals("Sun")) {
            day1.setText("Sat");
            day2.setText("Sun");
            day3.setText("Mon");
        } else if (day.equals("Mon")) {
            day1.setText("Sun");
            day2.setText("Mon");
            day3.setText("Tue");
        } else if (day.equals("Tue")) {
            day1.setText("Mon");
            day2.setText("Tue");
            day3.setText("Wed");
        } else if (day.equals("Wed")) {
            day1.setText("Tue");
            day2.setText("Wed");
            day3.setText("Tur");
        } else if (day.equals("Tur")) {
            day1.setText("Wed");
            day2.setText("Tur");
            day3.setText("Fri");
        } else if (day.equals("Fri")) {
            day1.setText("Tur");
            day2.setText("Fri");
            day3.setText("Sat");
        } else if (day.equals("Sat")) {
            day1.setText("Fri");
            day2.setText("Sat");
            day3.setText("Sun");
        }

        SimpleDateFormat currentDatemonth = new SimpleDateFormat("dd");
        String dayOfMonth = currentDatemonth.format(todayDate);

      Log.d(TAG, "onDateSet: dd: " + dayOfMonth);
        String date11 = dayOfMonth + "";
        date1.setText(date11);

        int countdate = Integer.valueOf(String.valueOf(dayOfMonth));
        int countdate11 = countdate-1;
        date1.setText(String.valueOf(countdate11));

        int countdate2 = Integer.valueOf(String.valueOf(dayOfMonth));
        int countdate12 = countdate2 ;
        date2.setText(String.valueOf(countdate12));

        int countdate3 = Integer.valueOf(String.valueOf(dayOfMonth));
        int countdate13 = countdate3 + 1;
        date3.setText(String.valueOf(countdate13));


        datecfm=true;
    }


    @Override
    protected void onResume() {
        super.onResume();
        bus.setImageResource(R.drawable.ic_bus_blue);
        discount.setImageResource(R.drawable.ic_percentage_gray);
        checked.setImageResource(R.drawable.ic_bookings_gray);
        user.setImageResource(R.drawable.ic_profile_gray);

        search.setTextColor(Color.parseColor("#000000"));
        offer.setTextColor(Color.parseColor("#91959d"));
        booking.setTextColor(Color.parseColor("#91959d"));
        profile.setTextColor(Color.parseColor("#91959d"));
    }



}