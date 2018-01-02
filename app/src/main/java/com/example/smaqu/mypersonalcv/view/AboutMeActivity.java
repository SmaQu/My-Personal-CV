package com.example.smaqu.mypersonalcv.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.smaqu.mypersonalcv.R;

public class AboutMeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ((TextView)findViewById(R.id.text_view_activity_about_me_about_me)).setText(R.string.about_me);
        ((TextView)findViewById(R.id.text_view_activity_about_me_signature)).setText(R.string.signature);
    }
}