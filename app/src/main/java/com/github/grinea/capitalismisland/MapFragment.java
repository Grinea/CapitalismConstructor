package com.github.grinea.capitalismisland;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.grinea.capitalismisland.model.GameData;
import com.github.grinea.capitalismisland.model.GameMap;
import com.github.grinea.capitalismisland.model.MapElement;
import com.github.grinea.capitalismisland.model.Structure;


public class MapFragment extends Fragment
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
        GameMap map = GameData.getInstance().getMap();
        View view = inflater.inflate(R.layout.fragment_map, container,  false);

        RecyclerView rv = view.findViewById(R.id.mapRecycler);

        rv.setLayoutManager(new GridLayoutManager(getActivity(), map.getRows(), GridLayoutManager.HORIZONTAL, false));

        MapAdapter adapter = new MapAdapter(getContext(), map);

        rv.setAdapter(adapter);

        return view;
    }

    private class MapAdapter extends RecyclerView.Adapter<MapViewHolder>
    {
        private GameMap src;
        private LayoutInflater li;

        public MapAdapter(Context context, GameMap src)
        {
            this.src = src;
            this.li = LayoutInflater.from(context);
        }

        @Override
        public int getItemCount()
        {
            return src.getCount();
        }

        @NonNull
        @Override
        public MapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            return new MapViewHolder(li, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull MapViewHolder holder, int position)
        {
            holder.bind(src.getElement(position));
        }
    }

    private class MapViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView structure;
        private ImageView background;

        public MapViewHolder(LayoutInflater li, ViewGroup parent)
        {
            super(li.inflate(R.layout.fragment_map_element, parent, false));

            int size = parent.getMeasuredHeight() / GameData.getInstance().getMap().getRows() + 1;

            ViewGroup.LayoutParams lp = itemView.getLayoutParams();

            lp.width = size;
            lp.height = size;

            structure =  itemView.findViewById(R.id.structure);
            background = itemView.findViewById(R.id.background);

            itemView.setOnClickListener((v) -> {

                Structure selStruct;

                FragmentManager fm = getFragmentManager();

                SelectorFragment sf = (SelectorFragment)fm.findFragmentById(R.id.selector);
                selStruct = sf.getSelStruct();

                if (selStruct == null)
                {
                    //todo go to inspect
                }
                else
                {
                    if (selStruct.getType() > 0)
                    {
                        //todo set the mapelement's structure and get recycler position
                        if (GameData.getInstance().getMap().isBuildable(1))
                        {
                            structure.setImageResource(selStruct.getImageID());
                            sf.clearSelStruct();
                        }
                    }
                    else
                    {
                        //todo just build it
                    }
                }
            });
        }

        public void bind (MapElement data)
        {
            switch (data.getGrassType())
            {
                case 1:
                    background.setImageResource(R.drawable.ic_grass1);
                    break;
                case 2:
                    background.setImageResource(R.drawable.ic_grass2);
                    break;
                case 3:
                    background.setImageResource(R.drawable.ic_grass3);
                    break;
                case 4:
                    background.setImageResource(R.drawable.ic_grass4);
                    break;
            }
        }
    }
}
