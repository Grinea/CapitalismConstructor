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
import com.github.grinea.capitalismisland.model.Settings;
import com.github.grinea.capitalismisland.model.Structure;

import java.util.Map;


public class MapFragment extends Fragment
{
    RecyclerView rv;

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

        rv = view.findViewById(R.id.mapRecycler);

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
        private ImageView structImg;
        private ImageView background;

        public MapViewHolder(LayoutInflater li, ViewGroup parent)
        {
            super(li.inflate(R.layout.fragment_map_element, parent, false));

            int size = parent.getMeasuredHeight() / GameData.getInstance().getMap().getRows() + 1;

            ViewGroup.LayoutParams lp = itemView.getLayoutParams();

            lp.width = size;
            lp.height = size;

            structImg =  itemView.findViewById(R.id.structure);
            background = itemView.findViewById(R.id.background);

            itemView.setOnClickListener((v) -> {

                FragmentManager fm = getFragmentManager();

                SelectorFragment sf = (SelectorFragment)fm.findFragmentById(R.id.selector);
                Structure selStruct = sf.getSelStruct();

                if (selStruct == null)
                {
                    //todo go to inspect
                }
                else
                {
                    if (((GameFragment)fm.findFragmentById(R.id.fragment_holder))
                            .build(selStruct,getAdapterPosition()))
                    {
                        sf.clearSelStruct();
                        structImg.setImageResource(selStruct.getImageID());
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

            if (data.getStructure() == null)
            {
                structImg.setImageDrawable(null);
            }
            else
            {
                structImg.setImageResource(data.getStructure().getImageID());
            }
        }


    }
}
