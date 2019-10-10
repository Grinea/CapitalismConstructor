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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.grinea.capitalismisland.model.GameData;
import com.github.grinea.capitalismisland.model.Structure;
import com.github.grinea.capitalismisland.model.StructureData;



public class SelectorFragment extends Fragment
{
    private Structure selStruct = null;

    public Structure getSelStruct() {
        return selStruct;
    }

    public void clearSelStruct() {
        this.selStruct = null;
        RecyclerView rv = getView().findViewById(R.id.selectorRecycler);
        rv.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_selector, container,  false);

        RecyclerView rv = view.findViewById(R.id.selectorRecycler);

        rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        SelectorAdapter adapter = new SelectorAdapter(getContext(), StructureData.getInstance());

        rv.setAdapter(adapter);

        return view;
    }

    private class SelectorAdapter extends RecyclerView.Adapter<SelectorViewHolder>
    {
        private StructureData src;
        private LayoutInflater li;

        public SelectorAdapter(Context context, StructureData src)
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
        private ImageView pic, background;
        private TextView price;
        private Structure structure = null;

        public SelectorViewHolder(LayoutInflater li, ViewGroup parent)
        {
            super(li.inflate(R.layout.fragment_selector_element, parent, false));

            pic =  itemView.findViewById(R.id.structure);
            price = itemView.findViewById(R.id.price);
            background = itemView.findViewById(R.id.background);

            itemView.setOnClickListener((v) -> {
                if (selStruct == structure)
                {
                    selStruct = null;
                    deselect();
                }
                else
                {
                    selStruct = structure;

                    deselectAll();

                    select();
                }
            });

        }

        public void bind (Structure struct)
        {
            pic.setImageResource(struct.getImageID());
            structure = struct;

            if (selStruct == structure)
            {
                select();
            }
            else
            {
                deselect();
            }

            switch (struct.getType())
            {
                case 0:
                    price.setText(String.valueOf(GameData.getInstance().getSettings().getRoadCost()));
                    break;
                case 1:
                    price.setText(String.valueOf(GameData.getInstance().getSettings().getHouseCost()));
                    break;
                case 2:
                    price.setText(String.valueOf(GameData.getInstance().getSettings().getCommCost()));
                    break;
            }
        }

        private void select()
        {
            background.setAlpha((float) 0.8);
        }

        private void deselect()
        {
            background.setAlpha((float) 0.0);
        }

        private void deselectAll()
        {
            RecyclerView rv = getView().findViewById(R.id.selectorRecycler);

            for (int ii = 0; ii < rv.getAdapter().getItemCount(); ii++) {

                SelectorViewHolder svh = (SelectorViewHolder) rv.findViewHolderForAdapterPosition(ii);
                if (svh != null) {
                    svh.deselect();
                }
            }
        }
    }


}
