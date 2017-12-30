package com.example.smaqu.mypersonalcv.presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.smaqu.mypersonalcv.adapter.CardViewAdapterMainActivity;
import com.example.smaqu.mypersonalcv.model.DatabaseHelper;
import com.example.smaqu.mypersonalcv.pojo.CardViewItem;
import com.example.smaqu.mypersonalcv.view.AboutApplicationActivity;
import com.example.smaqu.mypersonalcv.view.AboutMeActivity;
import com.example.smaqu.mypersonalcv.view.DetailActivity;
import com.example.smaqu.mypersonalcv.view.MainActivityInterface;

import java.util.List;

/**
 * Created by SmaQu on 2017-12-22.
 */

public class MainPresenter implements MainPresenterInterface, CardViewAdapterMainActivity.OnClickCallback {

    private MainActivityInterface mainView;
    private List<CardViewItem> cardViewItemList;
    public static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    public static final String BUNDLE_DESCRIPTION = "BUNDLE_DESC";

    public MainPresenter(MainActivityInterface mainView) {
        this.mainView = mainView;
        DatabaseHelper databaseHelper = new DatabaseHelper((Context) mainView);
        databaseHelper.createDatabase();
        cardViewItemList = databaseHelper.getCardViewList();
    }

    @Override
    public void createAdapter() {
        CardViewAdapterMainActivity adapter = new CardViewAdapterMainActivity(cardViewItemList);
        adapter.setOnItemCallback(this);
        mainView.createRecyclerView(adapter);
    }

    @Override
    public void openDialer(Context context) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + "123456789"));
        context.startActivity(intent);
    }

    @Override
    public void openAboutApp(Context context) {
        Intent intent = new Intent(context, AboutApplicationActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void openAboutMe(Context context) {
        Intent intent = new Intent(context, AboutMeActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onItemClick(View view, int p) {
        CardViewItem cardViewItem = cardViewItemList.get(p);
        Intent intent = new Intent(view.getContext(), DetailActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_DESCRIPTION, cardViewItem.getDescription());
        intent.putExtra(BUNDLE_EXTRAS, bundle);
        view.getContext().startActivity(intent);
    }
}