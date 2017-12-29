package com.example.smaqu.mypersonalcv.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.example.smaqu.mypersonalcv.R;
import com.example.smaqu.mypersonalcv.adapter.ListViewAdapterDetailActivity;
import com.example.smaqu.mypersonalcv.presenter.DetailPresenter;

import static com.example.smaqu.mypersonalcv.presenter.MainPresenter.BUNDLE_EXTRAS;

public class DetailActivity extends AppCompatActivity implements DetailActivityInterface {

    private DetailPresenter detailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailPresenter.openDialer(view.getContext());
            }
        });

        detailPresenter = new DetailPresenter(this);
        detailPresenter.createAdapter(this,getIntent().getBundleExtra(BUNDLE_EXTRAS));
    }

    @Override
    public void createListView(ListViewAdapterDetailActivity listViewAdapterDetailActivity) {
        ListView listView = findViewById(R.id.list_view_content_main);
        listView.setAdapter(listViewAdapterDetailActivity);
    }
}
