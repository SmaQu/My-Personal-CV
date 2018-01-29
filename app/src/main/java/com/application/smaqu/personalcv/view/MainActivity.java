package com.application.smaqu.personalcv.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.application.smaqu.personalcv.R;
import com.application.smaqu.personalcv.adapter.CardViewAdapterMainActivity;
import com.application.smaqu.personalcv.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainActivityInterface {

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.openDialer(view.getContext());
            }
        });

        mainPresenter = new MainPresenter(this);
        mainPresenter.createAdapter(getApplicationContext());
    }

    @Override
    public void createRecyclerView(CardViewAdapterMainActivity cardViewAdapterMainActivity) {
        RecyclerView recyclerView = findViewById(R.id.recycler_view_content_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cardViewAdapterMainActivity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about_me:
                mainPresenter.openAboutMe(this);
                break;
            case R.id.action_about_application:
                mainPresenter.openAboutApp(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}