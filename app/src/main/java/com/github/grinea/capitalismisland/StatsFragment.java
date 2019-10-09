package com.github.grinea.capitalismisland;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.grinea.capitalismisland.model.GameData;
import com.github.grinea.capitalismisland.model.GameMap;
import com.github.grinea.capitalismisland.model.MapElement;


public class StatsFragment extends Fragment
{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        GameData data = GameData.getInstance();

        View view = inflater.inflate(R.layout.fragment_game_stats, container,  false);

        TextView tTime = view.findViewById(R.id.val_time);
        TextView tMoney = view.findViewById(R.id.val_money);
        TextView tIncome = view.findViewById(R.id.val_income);
        TextView tPop = view.findViewById(R.id.val_pop);
        TextView tEmploy = view.findViewById(R.id.val_employment);

        tTime.setText(String.valueOf(data.getGameTime()));
        tMoney.setText(String.valueOf(data.getMoney()));
        tIncome.setText("Poor AF");
        tPop.setText("Lots ey");
        tEmploy.setText("Rockingham");

        return view;
    }


}
