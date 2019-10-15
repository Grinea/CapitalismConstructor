package com.grinea.capitalismconstructor.view;

/*
 * Recycler view base fragment for displaying and selecting available structures
 * for construction
 */

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

import com.grinea.capitalismconstructor.R;
import com.grinea.capitalismconstructor.model.GameData;
import com.grinea.capitalismconstructor.model.Settings;
import com.grinea.capitalismconstructor.model.Structure;
import com.grinea.capitalismconstructor.model.StructureData;

public class SelectorFragment extends Fragment
{
    private Structure selStruct = null;
    private RecyclerView rv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {

        View view =
                inflater.inflate(R.layout.fragment_selector, container, false);

        //create and configure recycler view
        rv = view.findViewById(R.id.selectorRecycler);
        rv.setLayoutManager(new LinearLayoutManager(getActivity(),
                                                    LinearLayoutManager.HORIZONTAL,
                                                    false));
        SelectorAdapter adapter =
                new SelectorAdapter(getContext(), StructureData.getInstance());
        rv.setAdapter(adapter);

        return view;
    }

    //Selector's adapter class
    private class SelectorAdapter extends RecyclerView.Adapter<SelectorViewHolder>
    {
        private StructureData src;
        private LayoutInflater li;

        SelectorAdapter(Context context, StructureData src)
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
        public SelectorViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                     int viewType)
        {
            return new SelectorViewHolder(li, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull SelectorViewHolder holder,
                                     int position)
        {
            holder.bind(src.getElement(position));
        }
    }

    //Selector's viewholder class
    private class SelectorViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView pic, background;
        private TextView price;
        private Structure structure = null;

        SelectorViewHolder(LayoutInflater li, ViewGroup parent)
        {
            super(li.inflate(R.layout.fragment_selector_element, parent,
                             false));

            //collect references
            pic = itemView.findViewById(R.id.structure);
            price = itemView.findViewById(R.id.price);
            background = itemView.findViewById(R.id.background);

            //OCL for highlighting and indicating selected structure
            itemView.setOnClickListener((v) -> {
                if (selStruct == structure)
                {
                    //double tap behaviour
                    selStruct = null;
                    deselect();
                } else
                {
                    selStruct = structure;

                    //manage selection highlighting
                    deselectAll();
                    select();
                }
            });

        }

        void bind(Structure struct)
        {
            Settings sts = GameData.getInstance().getSettings();
            pic.setImageResource(struct.getImageID());
            structure = struct;

            //return select highlighting if scrolling back to viewholder
            if (selStruct == structure)
            {
                select();
            } else
            {
                deselect();
            }

            //structure cost display by structure type
            switch (struct.getType())
            {
                case 0:
                    price.setText(String.valueOf(sts.getRoadCost()));
                    break;
                case 1:
                    price.setText(String.valueOf(sts.getHouseCost()));
                    break;
                case 2:
                    price.setText(String.valueOf(sts.getCommCost()));
                    break;
            }
        }

        //highlighting methods to indicate selection
        void select()
        {
            background.setAlpha((float) 0.8);
        }

        void deselect()
        {
            background.setAlpha((float) 0.0);
        }
    }

    Structure getSelStruct()
    {
        return selStruct;
    }

    //setter that only allows null and then clears selection
    void clearSelStruct()
    {
        this.selStruct = null;
        deselectAll();
    }

    //sets all items to be deselected
    private void deselectAll()
    {
        SelectorViewHolder svh = null;

        if (rv.getAdapter() != null)
        {
            /*doing it more than child count as child count occasionally misses
            a few just out of view, i assume a result of some kind of buffering
            effect based on scroll speed*/
            for (int ii = 0; ii < rv.getAdapter().getItemCount(); ii++)
            {
                svh = (SelectorViewHolder) rv
                        .findViewHolderForAdapterPosition(ii);
                if (svh != null)
                {
                    svh.deselect();
                }
            }
        }
    }
}
