package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.support.design.widget.Snackbar;
import android.support.design.widget.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityHomBinding;

public class Hom extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarHom.toolbar);
//        binding.appBarHom.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_hom);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hom, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_hom);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id=menuItem.getItemId();
        if (R.id.view_profile==id)
        {
            Intent i=new Intent(getApplicationContext(),view_profile.class);
            startActivity(i);
        }
        else if (R.id.change_password==id)
        {
            Intent i=new Intent(getApplicationContext(),change_password.class);
            startActivity(i);
        }
        else if (R.id.exam_notification==id)
        {
            Intent i=new Intent(getApplicationContext(),exam_notification.class);
            startActivity(i);
        }
        else if (R.id.notification==id)
        {
            Intent i=new Intent(getApplicationContext(),notification.class);
            startActivity(i);
        }
        else if (R.id.send_complaint==id)
        {
            Intent i=new Intent(getApplicationContext(),view_reply_complaint.class);
            startActivity(i);
        }
        else if (R.id.send_feedback==id)
        {
            Intent i=new Intent(getApplicationContext(),send_feedack.class);
            startActivity(i);
        }
        else if (R.id.view_career==id)
        {
            Intent i=new Intent(getApplicationContext(),view_career.class);
            startActivity(i);
        }
        else if (R.id.view_material==id)
        {
            Intent i=new Intent(getApplicationContext(),view_material.class);
            startActivity(i);
        }
        else if (R.id.view_result==id)
        {
            Intent i=new Intent(getApplicationContext(),view_result.class);
            startActivity(i);
        }
        return false;
    }
}