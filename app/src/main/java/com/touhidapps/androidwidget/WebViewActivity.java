package com.touhidapps.androidwidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;

public class WebViewActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonLoadSite, buttonLoadFromLocal;
    WebView webView;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        setTitle("Web View & Progress Bar Example");

        buttonLoadSite = (Button) findViewById(R.id.buttonLoadSite);
        buttonLoadFromLocal = (Button) findViewById(R.id.buttonLoadFromLocal);
        buttonLoadSite.setOnClickListener(this);
        buttonLoadFromLocal.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient()); // prevent open outside browser
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress < 100 && progressBar.getVisibility() == ProgressBar.GONE) {
                    progressBar.setVisibility(ProgressBar.VISIBLE);
                }
                progressBar.setProgress(progress);
                if (progress == 100) {
                    progressBar.setVisibility(ProgressBar.GONE);
                }
            }
        });



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonLoadSite:
                webView.loadUrl("http://touhidapps.com");
                break;

            case R.id.buttonLoadFromLocal:
                webView.loadUrl("file:///android_asset/index.html");
                break;
        }
    }
}
