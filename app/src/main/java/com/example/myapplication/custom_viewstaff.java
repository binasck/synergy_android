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

public class custom_viewstaff extends BaseAdapter {
    String[] name,lid,photo;
    Context context;

    public custom_viewstaff (Context applicationContext, String[] name,String[] lid,String[] photo){
        this.context=applicationContext;
        this.name=name;
        this.lid=lid;
        this.photo=photo;
    }
    @Override
    public int getCount() {
        return lid.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
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
            gridView=inflator.inflate(R.layout.custom_viewstaff,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView38);
        Button b1=(Button)gridView.findViewById(R.id.button7);
        ImageView img=(ImageView) gridView.findViewById(R.id.imageView3);



        tv1.setTextColor(Color.BLACK);


        tv1.setText(name[i]);


        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");

        String url="http://" + ip + ":5000"+photo[i];


        Picasso.with(context).load(url).into(img);

        b1.setTag(lid[i]);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String staff_id=v.getTag().toString();
                SharedPreferences.Editor ed=sh.edit();
                ed.putString("tolid",staff_id);
                ed.commit();
                context.startActivity(new Intent(context,Test.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

        return gridView;
    }
}
