package com.github.grinea.capitalismisland.view;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.grinea.capitalismisland.R;
import com.github.grinea.capitalismisland.model.GameData;


public class StatsFragment extends Fragment implements StatsObs
{
    private TextView tTime;
    private TextView tMoney;
    private TextView tIncome;
    private TextView tPop;
    private TextView tEmploy;

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

        View view = inflater.inflate(R.layout.fragment_game_stats, container, false);

        ImageView iTime = view.findViewById(R.id.ic_time);

        tTime = view.findViewById(R.id.val_time);
        tMoney = view.findViewById(R.id.val_money);
        tIncome = view.findViewById(R.id.val_income);
        tPop = view.findViewById(R.id.val_pop);
        tEmploy = view.findViewById(R.id.val_employment);

        tTime.setText(String.valueOf(data.getGameTime()));
        tMoney.setText(String.valueOf(data.getMoney()));
        if (data.getGameTime() == 0)
        {
            tIncome.setText("-");
        } else
        {
            tIncome.setText(String.valueOf(data.getIncome()));
        }
        tPop.setText(String.valueOf(data.getPop()));

        if (data.getPop() == 0)
        {
            tEmploy.setText("-");
        } else
        {
            tEmploy.setText(String.valueOf((int) (data.getEmploy() * 100)));
        }
        iTime.setOnClickListener((v) -> {
            data.timeStep();
            if (data.getMoney() < 0)
            {
                gameOver();
            }
        });

        tTime.setOnClickListener((v) -> {
            data.timeStep();
            if (data.getMoney() < 0)
            {
                gameOver();
            }
        });

        return view;
    }

    @Override
    public void timeUpdate(int time)
    {
        tTime.setText(String.valueOf(time));
    }

    @Override
    public void moneyUpdate(int money)
    {
        tMoney.setText(String.valueOf(money));
    }

    @Override
    public void incomeUpdate(int income)
    {
        tIncome.setText(String.valueOf(income));
    }

    @Override
    public void popUpdate(int pop)
    {
        tPop.setText(String.valueOf(pop));
    }

    @Override
    public void employmentUpdate(int pop, int jobs)
    {
        if (pop == 0)
        {
            tEmploy.setText("--%");
        } else
        {
            double empRate = Math.min((double) jobs / (double) pop, 1);
            tEmploy.setText((int) (empRate * 100) + " %");
        }
    }

    public void gameOver()
    {
        Toast gameOver = Toast.makeText(getActivity().getApplicationContext(), "GAME OVER", Toast.LENGTH_LONG);
        gameOver.setGravity(Gravity.CENTER, 0, 0);
        gameOver.show();

    }
}
