package com.example.smaqu.mypersonalcv.pojo;

import java.util.List;

/**
 * Created by SmaQu on 2017-12-22.
 */

public class CardViewItem {
    private int image;
    private String description;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DetailListViewItem> getCardViewDetailListView() {
        return cardViewDetailListView;
    }

    public void setCardViewDetailListView(List<DetailListViewItem> cardViewDetailListView) {
        this.cardViewDetailListView = cardViewDetailListView;
    }

    private List<DetailListViewItem> cardViewDetailListView;
}
