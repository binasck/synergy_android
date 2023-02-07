package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class custom_career  extends BaseAdapter {
    String[] carer,desrip,date,time;
    Context context;
    public custom_career(Context applicationContext, String[] date, String[] time, String[] carer, String[] desrip) {
        this.context=applicationContext;
        this.date=date;
        this.time=time;
        this.carer=carer;
        this.desrip=desrip;
    }

    @Override
    public int getCount() {
        return date.length;
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
            gridView=inflator.inflate(R.layout.custom_career,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView32);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView33);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView34);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView35);



        tv1.setText(date[i]);
        tv2.setText(time[i]);
        tv3.setText(carer[i]);
        tv4.setText(desrip[i]);



        return gridView;
    }
}
