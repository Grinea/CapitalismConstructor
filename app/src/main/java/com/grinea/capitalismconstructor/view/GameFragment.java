package com.grinea.capitalismconstructor.view;

//Game play fragment that is comprised of map, selector and stats fragments

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.grinea.capitalismconstructor.R;
import com.grinea.capitalismconstructor.model.GameData;
import com.grinea.capitalismconstructor.model.GameMap;
import com.grinea.capitalismconstructor.model.MapElement;
import com.grinea.capitalismconstructor.model.Structure;

public class GameFragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_game, container, false);

        FragmentManager fm = getFragmentManager();

        //create fragments
        MapFragment mapFrag = new MapFragment();
        StatsFragment statsFrag = new StatsFragment();
        SelectorFragment selFrag = new SelectorFragment();

        //load observers
        GameData.getInstance().setObs(statsFrag);

        //commit fragments
        fm.beginTransaction().add(R.id.map, mapFrag).add(R.id.stats, statsFrag)
          .add(R.id.selector, selFrag).commit();
        return view;
    }

    boolean build(Structure structure, int position)
    {
        GameData gd = GameData.getInstance();
        GameMap src = gd.getMap();
        MapElement mapEl = src.getElement(position);

        //reject if elements not empty
        if (mapEl.getStructure() != null)
        {
            Toast toast = Toast.makeText(getContext(), "Space occupied!",
                                         Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return false;
        }

        //if enough funds
        if (gd.getMoney() >= structure.getCost())
        {
            //if connected to a road
            if (src.isBuildable(position) || structure.getType() == 0)
            {
                mapEl.setStructure(structure);
                gd.build(structure);
                getView().findViewById(R.id.val_money);
                gd.updateMapElement(gd.getMap().getElement(position));
                return true;
            } else // not connect to road
            {
                Toast toast =
                        Toast.makeText(getContext(), "Not connected to road",
                                       Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                return false;
            }
        } else //not enough capitalism
        {
            Toast toast = Toast.makeText(getContext(), "Too poor! Be richer",
                                         Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return false;
        }
    }
}
