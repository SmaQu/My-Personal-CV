package com.example.smaqu.mypersonalcv.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.example.smaqu.mypersonalcv.R;

public class AboutApplicationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_application);
        ((WebView) findViewById(R.id.web_view_activity_about_application)).loadUrl("file:///android_asset/AboutApp.html");
    }
}