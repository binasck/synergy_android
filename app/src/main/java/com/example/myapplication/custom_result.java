package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class custom_result  extends BaseAdapter {
    String[] mark,date,exm_name;
    Context context;
    public custom_result(Context applicationContext, String[] date, String[] exm_name, String[] mark) {
    this.context=applicationContext;
    this.exm_name=exm_name;
    this.mark=mark;
    this.date=date;
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
            gridView=inflator.inflate(R.layout.custom_result,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView26);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView27);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView28);



        tv1.setText(date[i]);
        tv2.setText(exm_name[i]);
        tv3.setText(mark[i]);



        return gridView;
    }
}
