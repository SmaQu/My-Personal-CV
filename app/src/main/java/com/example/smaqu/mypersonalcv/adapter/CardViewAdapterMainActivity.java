package com.example.smaqu.mypersonalcv.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smaqu.mypersonalcv.R;
import com.example.smaqu.mypersonalcv.pojo.CardViewItem;

import java.util.List;

/**
 * Created by SmaQu on 2017-12-22.
 */

public class CardViewAdapterMainActivity extends RecyclerView.Adapter{

    private final Context context;
    private final List<CardViewItem> listOfCardViewItems;

    public CardViewAdapterMainActivity(Context context, List<CardViewItem> listOfCardViewItems) {
        this.context = context;
        this.listOfCardViewItems = listOfCardViewItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_item, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CardViewItem cardViewItem = listOfCardViewItems.get(position);
        ((MyViewHolder) holder).image.setImageResource(cardViewItem.getImage());
        ((MyViewHolder) holder).description.setText(cardViewItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return listOfCardViewItems.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView description;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_view_card_view_item_image);
            description = itemView.findViewById(R.id.text_view_card_view_item_desc);
        }
    }

}
