package com.example.smaqu.mypersonalcv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smaqu.mypersonalcv.R;
import com.example.smaqu.mypersonalcv.pojo.DetailListViewItem;

import java.util.List;

/**
 * Created by SmaQu on 2017-12-23.
 */

public class ListViewAdapterDetailActivity extends BaseAdapter {

    private final Context context;
    private final List<DetailListViewItem> listOfElements;

    public ListViewAdapterDetailActivity(Context context, List<DetailListViewItem> listOfElements) {
        this.context = context;
        this.listOfElements = listOfElements;
    }

    @Override
    public int getCount() {
        return listOfElements.size();
    }

    @Override
    public DetailListViewItem getItem(int i) {
        return listOfElements.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_view_item, viewGroup,false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        DetailListViewItem detailListViewItem = getItem(i);
        viewHolder.imageView.setImageResource(context.getResources().getIdentifier(detailListViewItem.getIco(),"drawable",context.getPackageName()));
        viewHolder.topic.setText(detailListViewItem.getTopic());
        viewHolder.info.setText(detailListViewItem.getInfo());
        viewHolder.date.setText(detailListViewItem.getDate());
        return view;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView topic;
        TextView info;
        TextView date;

        ViewHolder(View view) {
            imageView = view.findViewById(R.id.image_view_list_view_item_icon);
            topic = view.findViewById(R.id.text_view_list_view_item_topic);
            info = view.findViewById(R.id.text_view_list_view_item_info);
            date = view.findViewById(R.id.text_view_list_view_item_date);
        }
    }
}
