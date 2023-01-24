package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class custom_notification extends BaseAdapter {
    String[] from_lid,noti,date;
    Context context;
    public custom_notification(Context applicationContext, String[]from_lid, String[] date, String[] noti) {
        this.context=applicationContext;
        this.from_lid=from_lid;
        this.date=date;
        this.noti=noti;

    }


    @Override
    public int getCount() {
        return from_lid.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if (view == null) {
            gridView = new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView = inflator.inflate(R.layout.custom_notification, null);

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView21);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView22);


        tv1.setText(date[i]);
        tv3.setText(noti[i]);


        return gridView;
    }}
