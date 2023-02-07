package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class custom_exam_notification extends BaseAdapter {
    String[] from_name,from_lid,noti,date;
    private Context context;

    public custom_exam_notification(Context appcontext,String[]from_name,String[]from_lid,String[]noti,String[]date)
    {
        this.context=appcontext;
        this.from_lid=from_lid;
        this.noti=noti;
        this.date=date;
        this.from_name=from_name;




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
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(view==null)
        {
            gridView=new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView=inflator.inflate(R.layout.custom_exam_notification,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView23);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView24);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView25);

        tv1.setText(date[i]);
        tv2.setText(from_name[i]);
        tv3.setText(noti[i]);



        return gridView;
    }
}
