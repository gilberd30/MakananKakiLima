package com.aplikasi.projectwebview;

import androidx.appcompat.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    WebView web;
    WebSettings websettings1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        web = (WebView)findViewById(R.id.testWebView);
        websettings1 = web.getSettings();

        web.setWebViewClient(new WebViewClient());
        web.loadUrl("https://sites.google.com/view/jualskincaretermurah2020/halaman-muka");
    }
}