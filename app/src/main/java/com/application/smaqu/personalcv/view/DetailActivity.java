package com.application.smaqu.personalcv.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.application.smaqu.personalcv.R;
import com.application.smaqu.personalcv.adapter.ListViewAdapterDetailActivity;
import com.application.smaqu.personalcv.presenter.DetailPresenter;

import static com.application.smaqu.personalcv.presenter.MainPresenter.BUNDLE_EXTRAS;

public class DetailActivity extends AppCompatActivity implements DetailActivityInterface {

    private DetailPresenter detailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        detailPresenter = new DetailPresenter(this);
        detailPresenter.createAdapter(this, getIntent().getBundleExtra(BUNDLE_EXTRAS));
    }

    @Override
    public void createListView(ListViewAdapterDetailActivity listViewAdapterDetailActivity) {
        ListView listView = findViewById(R.id.list_view_content_main);
        listView.setAdapter(listViewAdapterDetailActivity);
    }
}