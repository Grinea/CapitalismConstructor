package com.github.grinea.capitalismisland;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.grinea.capitalismisland.model.GameData;
import com.github.grinea.capitalismisland.model.StructureData;


public class SelectorFragment extends Fragment
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
        StructureData data = StructureData.getInstance();
        View view = inflater.inflate(R.layout.fragment_selector, container,  false);

        RecyclerView rv = view.findViewById(R.id.selectorRecycler);

        rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        SelectorAdapter adapter = new SelectorAdapter(getContext());

        rv.setAdapter(adapter);

        return view;
    }

    private class SelectorAdapter extends RecyclerView.Adapter<MapViewHolder>
    {
        private StructureData src;
        private LayoutInflater li;

        public MapAdapter(Context context)
        {
            this.src = StructureData.getInstance();
            this.li = LayoutInflater.from(context);
        }

        @Override
        public int getItemCount()
        {
            return src.getCount();
        }

        @NonNull
        @Override
        public SelectorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            return new SelectorViewHolder(li, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull SelectorViewHolder holder, int position)
        {
            holder.bind(src.getElement(position));
        }
    }

    private class SelectorViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView structure;
        private ImageView background;

        public SelectorViewHolder(LayoutInflater li, ViewGroup parent)
        {
            super(li.inflate(R.layout.fragment_map_element, parent, false));

            int size = parent.getMeasuredHeight() / GameData.getInstance().getMap().getRows() + 1;

            ViewGroup.LayoutParams lp = itemView.getLayoutParams();

            lp.width = size;
            lp.height = size;

            structure =  itemView.findViewById(R.id.structure);
            background = itemView.findViewById(R.id.background);
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
