package com.application.smaqu.personalcv.presenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.application.smaqu.personalcv.R;
import com.application.smaqu.personalcv.adapter.CardViewAdapterMainActivity;
import com.application.smaqu.personalcv.model.DatabaseHelper;
import com.application.smaqu.personalcv.pojo.CardViewItem;
import com.application.smaqu.personalcv.view.AboutApplicationActivity;
import com.application.smaqu.personalcv.view.AboutMeActivity;
import com.application.smaqu.personalcv.view.DetailActivity;
import com.application.smaqu.personalcv.view.MainActivityInterface;

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
    public void createAdapter(Context context) {
        CardViewAdapterMainActivity adapter = new CardViewAdapterMainActivity(context,cardViewItemList);
        adapter.setOnItemCallback(this);
        mainView.createRecyclerView(adapter);
    }

    @Override
    public void openDialer(Context context) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + context.getResources().getString(R.string.phone_number)));
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