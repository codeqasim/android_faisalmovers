package com.faisalmovers.travels.bus;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Webviewinvoice extends AppCompatActivity {

    private int progressStatus = 0;
    private Handler handler = new Handler();
    WebView web_view;
    String urlweb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webviewinvoice);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             urlweb = extras.getString("weburl");
            //The key argument here must match that used in the other activity
        }

        web_view = (WebView) findViewById(R.id.webview);
        final ProgressBar pb = (ProgressBar) findViewById(R.id.pb);
        final TextView tv = (TextView) findViewById(R.id.tv);
        pb.setProgressTintList(ColorStateList.valueOf(Color.RED));

        web_view.getSettings().setLoadsImagesAutomatically(true);
        web_view.getSettings().setJavaScriptEnabled(true);
        web_view.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        web_view.loadUrl(urlweb);

                new Thread(new Runnable() {
                    public void run() {
                        while (progressStatus < 100) {
                            //progressStatus = doWork();
                            progressStatus +=1;

                            //Try to sleep the thread for 20 milliseconds
                            try{
                                Thread.sleep(5000);




                            }catch(InterruptedException e){
                                e.printStackTrace();
                            }
                            handler.post(new Runnable() {
                                public void run() {
                                    pb.setProgress(progressStatus);
                                    tv.setText(progressStatus+"");


                                }
                            });
                        }


                        if(progressStatus==100)
                        {
                            Intent intent = new Intent(Webviewinvoice.this, BingoBusBookingHistoryActivity.class);
                            finish();
                            startActivity(intent);
                        }

                    }


                }).start();




    }
    public void onBackPressed(){

        Intent intent = new Intent(Webviewinvoice.this, BingoBusBookingHistoryActivity.class);
        finish();
        startActivity(intent);

    }

}
