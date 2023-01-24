package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class custom_complaint extends BaseAdapter {
    String[] complaint,date,reply,status;
    private Context context;

    public custom_complaint(Context appcontext, String[]complaint, String[]date, String[]reply, String[]status)
    {
        this.context=appcontext;
        this.complaint=complaint;
        this.date=date;
        this.reply=reply;
        this.status=status;




    }

    @Override
    public int getCount() {
        return complaint.length;
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
            gridView=inflator.inflate(R.layout.custom_complaint,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView21);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView22);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView36);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView37);



        tv1.setText(date[i]);
        tv2.setText(complaint[i]);
        tv3.setText(reply[i]);
        tv4.setText(status[i]);



        return gridView;
    }
}
