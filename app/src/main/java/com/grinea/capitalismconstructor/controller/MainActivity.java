package com.grinea.capitalismconstructor.controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;

import com.grinea.capitalismconstructor.model.GameData;
import com.grinea.capitalismconstructor.model.GameDatabase;
import com.grinea.capitalismconstructor.view.GameFragment;
import com.grinea.capitalismconstructor.view.HomeFragment;
import com.grinea.capitalismconstructor.R;
import com.grinea.capitalismconstructor.view.MapFragment;
import com.grinea.capitalismconstructor.view.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connect to or create, then load database
        GameDatabase db = new GameDatabase(MainActivity.this);
        GameData.getInstance().loadDB(db);

        //set default fragment
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.fragment_holder, new HomeFragment()).commit();

        //configure OCL for navigation
        BottomNavigationView botNav = findViewById(R.id.bot_nav_bar);
        botNav.setOnNavigationItemSelectedListener((v) -> {

            Fragment selFrag = null;

            switch (v.getItemId())
            {
                case R.id.nav_home:
                    selFrag = new HomeFragment();
                    break;
                case R.id.nav_settings:
                    selFrag = new SettingsFragment();
                    break;
                case R.id.nav_play:
                    selFrag = new GameFragment();
                    break;
            }

            fm.beginTransaction().replace(R.id.fragment_holder, selFrag).commit();

            return true;
        });
    }

}
