package com.application.smaqu.personalcv.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.smaqu.personalcv.R;
import com.application.smaqu.personalcv.pojo.CardViewItem;

import java.util.List;

/**
 * Created by SmaQu on 2017-12-22.
 */

public class CardViewAdapterMainActivity extends RecyclerView.Adapter {

    private OnClickCallback onClickCallback;
    private Context context;
    private final List<CardViewItem> listOfElements;

    public CardViewAdapterMainActivity(Context context, List<CardViewItem> listOfElements) {
        this.context = context;
        this.listOfElements = listOfElements;
    }

    public interface OnClickCallback {
        void onItemClick(View view, int p);
    }

    public void setOnItemCallback(final OnClickCallback onItemCallback) {
        this.onClickCallback = onItemCallback;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CardViewItem cardViewItem = listOfElements.get(position);
        ((MyViewHolder) holder).image.setImageResource(context.getResources().getIdentifier(cardViewItem.getImage(),"drawable",context.getPackageName()));
        ((MyViewHolder) holder).description.setText(cardViewItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return listOfElements.size();
    }

    private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        TextView description;
        View container;

        MyViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_view_card_view_item_image);
            description = itemView.findViewById(R.id.text_view_card_view_item_desc);
            container = itemView.findViewById(R.id.card_view_item);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onClickCallback.onItemClick(view, getAdapterPosition());
        }
    }

}