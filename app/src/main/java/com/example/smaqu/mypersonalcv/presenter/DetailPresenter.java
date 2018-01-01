package com.example.smaqu.mypersonalcv.presenter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.example.smaqu.mypersonalcv.adapter.ListViewAdapterDetailActivity;
import com.example.smaqu.mypersonalcv.model.DatabaseHelper;
import com.example.smaqu.mypersonalcv.view.DetailActivityInterface;

import static com.example.smaqu.mypersonalcv.presenter.MainPresenter.BUNDLE_DESCRIPTION;

/**
 * Created by SmaQu on 2017-12-23.
 */

public class DetailPresenter implements DetailPresenterInterface {

    private DatabaseHelper databaseHelper;
    private DetailActivityInterface detailView;

    public DetailPresenter(DetailActivityInterface detailActivityInterface) {
        this.detailView = detailActivityInterface;
        databaseHelper = new DatabaseHelper((Context) detailView);
        databaseHelper.createDatabase();
    }

    @Override
    public void createAdapter(Context context, Bundle bundle) {
        String description = bundle.getString(BUNDLE_DESCRIPTION);
        changeToolbarTitle(context, description);
        ListViewAdapterDetailActivity listViewAdapterDetailActivity = new ListViewAdapterDetailActivity(context, databaseHelper.getListViewList(description));
        detailView.createListView(listViewAdapterDetailActivity);
    }

    private void changeToolbarTitle(Context context, String title) {
        ((Activity) context).setTitle(title);
    }
}