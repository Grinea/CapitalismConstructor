package com.grinea.capitalismconstructor.view;

/*
 * Settings screen fragment, allowing for changing the 3 required settings as well as
 * resetting the game
 */

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.grinea.capitalismconstructor.R;
import com.grinea.capitalismconstructor.model.GameData;
import com.grinea.capitalismconstructor.model.Settings;

public class SettingsFragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View view =
                inflater.inflate(R.layout.fragment_settings, container, false);
        Settings sts = GameData.getInstance().getSettings();

        //collect references
        Button save = view.findViewById(R.id.save);
        Button reset = view.findViewById(R.id.reset);
        save.setEnabled(false);

        EditText cash = view.findViewById(R.id.starting_cash);
        EditText width = view.findViewById(R.id.map_width);
        EditText height = view.findViewById(R.id.map_height);

        //set current values
        cash.setText(String.valueOf(sts.getInitialMoney()));
        width.setText(String.valueOf(sts.getMapWidth()));
        height.setText(String.valueOf(sts.getMapHeight()));

        //sets TCLs for the settings to enable save button
        cash.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after)
            {
                //this page has been left intentionally blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count)
            {
                //this page was left blank accidentally
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                save.setEnabled(true);
            }
        });

        width.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after)
            {
                //this page has been left intentionally blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count)
            {
                //this page was left blank accidentally
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                save.setEnabled(true);
            }
        });

        height.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after)
            {
                //this page has been left intentionally blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count)
            {
                //this page was left blank accidentally
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                save.setEnabled(true);
            }
        });


        reset.setOnClickListener((v) -> {
            GameData gd = GameData.getInstance();
            //clears data and resets settings to default
            gd.resetData();
            gd.setSettings(new Settings());
        });

        //OCL for save button, sets to entered value or default when invalid or too low
        save.setOnClickListener((v) -> {
            int newCash, newWidth, newHeight;
            try
            {
                newCash = Integer.parseInt(cash.getText().toString());
            } catch (NumberFormatException e)
            {
                newCash = 0;
            }
            sts.setInitialMoney(Math.max(0, newCash));

            try
            {
                newWidth = Integer.parseInt(width.getText().toString());
            } catch (NumberFormatException e)
            {
                newWidth = 3;
            }
            sts.setMapWidth(Math.max(3, newWidth));

            try
            {
                newHeight = Integer.parseInt(height.getText().toString());
            } catch (NumberFormatException e)
            {
                newHeight = 3;
            }
            sts.setMapHeight(Math.max(3, newHeight));

            GameData gd = GameData.getInstance();
            gd.setSettings(sts);
            gd.resetData();
        });

        return view;
    }

}
