package com.github.grinea.capitalismisland;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.github.grinea.capitalismisland.model.Structure;

public class GameFragment extends Fragment
{
    private Structure selStruct = null;
    private MapFragment mapFrag;
    private StatsFragment statsFrag;
    private SelectorFragment selFrag;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_game, container, false);

        FragmentManager fm = getFragmentManager();

        mapFrag = new MapFragment();
        statsFrag = new StatsFragment();
        selFrag = new SelectorFragment();

        fm.beginTransaction()
                .add(R.id.map, mapFrag)
                .add(R.id.stats, statsFrag)
                .add(R.id.selector, selFrag).commit();
        return view;
    }

    public Structure getSelStruct()
    {
        return selStruct;
    }

    public void setSelStruct(Structure selStruct)
    {
        this.selStruct = selStruct;
    }

    public MapFragment getMapFrag() {
        return mapFrag;
    }

    public StatsFragment getStatsFrag() {
        return statsFrag;
    }

    public SelectorFragment getSelFrag() {
        return selFrag;
    }
}
