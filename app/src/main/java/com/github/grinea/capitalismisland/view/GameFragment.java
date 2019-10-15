package com.github.grinea.capitalismisland.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.github.grinea.capitalismisland.R;
import com.github.grinea.capitalismisland.model.GameData;
import com.github.grinea.capitalismisland.model.GameMap;
import com.github.grinea.capitalismisland.model.MapElement;
import com.github.grinea.capitalismisland.model.Structure;

public class GameFragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_game, container, false);

        FragmentManager fm = getFragmentManager();

        MapFragment mapFrag = new MapFragment();
        StatsFragment statsFrag = new StatsFragment();
        SelectorFragment selFrag = new SelectorFragment();

        GameData.getInstance().setObs(statsFrag);

        fm.beginTransaction()
                .add(R.id.map, mapFrag)
                .add(R.id.stats, statsFrag)
                .add(R.id.selector, selFrag).commit();
        return view;
    }

    public boolean build(Structure structure, int position)
    {
        GameData gd = GameData.getInstance();
        GameMap src = gd.getMap();
        MapElement mapEl = src.getElement(position);

        if (mapEl.getStructure() != null)
        {
            return false;
        }

        if (gd.getMoney() >= structure.getCost() &&
                (src.isBuildable(position) || structure.getType() == 0))
        {
            mapEl.setStructure(structure);
            gd.build(structure);
            getView().findViewById(R.id.val_money);
            return true;
        }
        else
        {
            return false;
        }
    }
}
