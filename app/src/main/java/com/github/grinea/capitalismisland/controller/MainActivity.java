package com.github.grinea.capitalismisland.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.github.grinea.capitalismisland.view.GameFragment;
import com.github.grinea.capitalismisland.view.HomeFragment;
import com.github.grinea.capitalismisland.R;
import com.github.grinea.capitalismisland.view.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();

        fm.beginTransaction().add(R.id.fragment_holder, new HomeFragment()).commit();

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
