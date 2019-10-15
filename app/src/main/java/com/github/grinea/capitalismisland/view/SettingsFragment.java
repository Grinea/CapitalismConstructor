package com.github.grinea.capitalismisland.view;

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

import com.github.grinea.capitalismisland.R;
import com.github.grinea.capitalismisland.model.GameData;
import com.github.grinea.capitalismisland.model.Settings;

import org.w3c.dom.Text;

public class SettingsFragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        Settings sts = GameData.getInstance().getSettings();

        Button save = view.findViewById(R.id.save);
        Button reset = view.findViewById(R.id.reset);
        save.setEnabled(false);

        EditText cash = view.findViewById(R.id.starting_cash);
        EditText width = view.findViewById(R.id.map_width);
        EditText height = view.findViewById(R.id.map_height);

        cash.setText(String.valueOf(sts.getInitialMoney()));
        width.setText(String.valueOf(sts.getMapWidth()));
        height.setText(String.valueOf(sts.getMapHeight()));

        cash.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                //this page has been left intentionally blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
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
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                //this page has been left intentionally blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
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
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                //this page has been left intentionally blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                //this page was left blank accidentally
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                save.setEnabled(true);
            }
        });

        reset.setOnClickListener((v) ->{
            GameData gd = GameData.getInstance();
            gd.resetData();
            gd.setSettings(new Settings());
        });

        save.setOnClickListener((v) ->{
            int newCash, newWidth, newHeight;
            try
            {
                newCash = Integer.parseInt(cash.getText().toString());
            }
            catch (NumberFormatException e)
            {
                newCash = 0;
            }
            sts.setInitialMoney(Math.max(0,newCash));

            try
            {
                newWidth = Integer.parseInt(width.getText().toString());
            }
            catch (NumberFormatException e)
            {
                newWidth = 3;
            }
            sts.setMapWidth(Math.max(3,newWidth));

            try
            {
                newHeight = Integer.parseInt(height.getText().toString());
            }
            catch (NumberFormatException e)
            {
                newHeight = 3;
            }
            sts.setMapHeight(Math.max(3,newHeight));

            GameData gd = GameData.getInstance();
            gd.setSettings(sts);
            gd.resetData();
        });

        return view;
    }

}
