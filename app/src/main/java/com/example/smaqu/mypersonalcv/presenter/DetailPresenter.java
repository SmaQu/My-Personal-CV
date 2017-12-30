package com.example.smaqu.mypersonalcv.presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
        ListViewAdapterDetailActivity listViewAdapterDetailActivity = new ListViewAdapterDetailActivity(context, databaseHelper.getListViewList(bundle.getString(BUNDLE_DESCRIPTION)));
        detailView.createListView(listViewAdapterDetailActivity);
    }

    @Override
    public void openDialer(Context context) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + "123456789"));
        context.startActivity(intent);
    }
}