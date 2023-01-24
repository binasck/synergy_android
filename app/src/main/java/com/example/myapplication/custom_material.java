package com.example.myapplication;

import static android.support.v4.content.ContextCompat.getSystemService;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.io.File;

public class custom_material extends BaseAdapter {

    String[] name,materil,date,staff_name;
    Context context;
    public custom_material(Context applicationContext, String[] date, String[] staff_name, String[] name, String[] materil) {
    this.context=applicationContext;
    this.date=date;
    this.name=name;
    this.materil=materil;
    this.staff_name=staff_name;




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
            gridView=inflator.inflate(R.layout.custom_material,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView29);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView30);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView31);
        ImageButton ib=(ImageButton)gridView.findViewById(R.id.imageButton);
        ib.setTag(materil[i]);


        tv1.setText(date[i]);
        tv2.setText(staff_name[i]);
        tv3.setText(name[i]);




        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                String ip = sh.getString("ip", "");

                open_file("http://" + ip + ":5000/"+v.getTag().toString());



            }

        });





        return gridView;
    }

    public void open_file(String file_) {
        Uri uri =Uri.parse(file_);
        String mime = context.getContentResolver().getType(uri);


        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, mime);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(intent);
    }


    }

