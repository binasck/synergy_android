package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
EditText address;
Button connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        address=(EditText) findViewById(R.id.editTextTextPersonName7);
        connect=(Button) findViewById(R.id.button6);
        connect.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        final String ip_address = address.getText().toString();
        if (ip_address.length()==0){
            address.setError("Missing");
        }
        else {

            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor ed = sh.edit();
            ed.putString("ip", ip_address);
            ed.commit();
            Intent it = new Intent(getApplicationContext(), login.class);
            startActivity(it);
        }
    }
}