package com.faisalmovers.travels.bus;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import util.Url;

public class WebMainActivity extends Url {

    ProgressBar progressBar;
    WebView webinvoice;
    String link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splace_screen_ws);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            link = extras.getString("weblinkid");

        }


        link =invoicedetailsuser+link;
        webinvoice= (WebView) findViewById(R.id.webinvoice);
        webinvoice.getSettings().setSupportZoom(true);
        webinvoice.getSettings().setBuiltInZoomControls(true);
        webinvoice.getSettings().setDisplayZoomControls(false);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2) ;

       progressBar.setVisibility(View.VISIBLE);
        webinvoice.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
               // setProgressBarVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                progressBar.setVisibility(View.GONE);
              //  setProgressBarVisibility(View.GONE);
            }
        });

        webinvoice.loadUrl(link);
    }
}
