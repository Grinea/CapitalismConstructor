package com.github.grinea.capitalismisland.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.grinea.capitalismisland.R;
import com.github.grinea.capitalismisland.model.GameData;
import com.github.grinea.capitalismisland.model.GameMap;
import com.github.grinea.capitalismisland.model.MapElement;
import com.github.grinea.capitalismisland.model.Structure;


public class MapFragment extends Fragment
{
    private static final int PHOTO_REQUEST_CODE = 1;
    private MapElement selElem;

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
        View view = inflater.inflate(R.layout.fragment_map, container, false);

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
        private MapElement data;


        public MapViewHolder(LayoutInflater li, ViewGroup parent)
        {
            super(li.inflate(R.layout.fragment_map_element, parent, false));

            int size = parent.getMeasuredHeight() / GameData.getInstance().getMap().getRows() + 1;

            ViewGroup.LayoutParams lp = itemView.getLayoutParams();

            lp.width = size;
            lp.height = size;

            structImg = itemView.findViewById(R.id.structure);
            background = itemView.findViewById(R.id.background);

            itemView.setOnClickListener((v) -> {

                selElem = GameData.getInstance().getMap().getElement(getAdapterPosition());
                FragmentManager fm = getFragmentManager();

                SelectorFragment sf = (SelectorFragment) fm.findFragmentById(R.id.selector);
                Structure selStruct = sf.getSelStruct();


                if (selStruct == null)
                {
                    if (data.getStructure() != null)
                    {
                        inspectPopUp(getAdapterPosition());
                    }
                } else
                {
                    if (((GameFragment) fm.findFragmentById(R.id.fragment_holder)).build(selStruct, getAdapterPosition()))
                    {
                        sf.clearSelStruct();
                        selElem.setOwnerName(selStruct.getDefaultName());
                        structImg.setImageResource(selStruct.getImageID());
                        GameData.getInstance().updateMapElement(selElem);
                    }
                }
            });
        }

        public void bind(MapElement data)
        {
            this.data = data;
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
            } else if (data.getImage() != null)
            {
                structImg.setImageBitmap(data.getImage());
            } else
            {
                structImg.setImageResource(data.getStructure().getImageID());
            }
        }

    }

    public void inspectPopUp(int mapPos)
    {
        MapElement dataElem = GameData.getInstance().getMap().getElement(mapPos);
        Structure structure = dataElem.getStructure();
        View window = getLayoutInflater().inflate(R.layout.popup_inspect, null);

        ImageView picture = window.findViewById(R.id.picture);
        EditText ownerName = window.findViewById(R.id.owner_name);
        TextView position = window.findViewById(R.id.position);
        TextView type = window.findViewById(R.id.structure_type);

        Button demolish = window.findViewById(R.id.demolish);
        Button addPhoto = window.findViewById(R.id.add_photo);

        picture.setImageResource(structure.getImageID());
        ownerName.setText(dataElem.getOwnerName());

        int cols = GameData.getInstance().getMap().getCols();
        String pos = "X: " + (mapPos / cols) + " Y: " + (mapPos % cols);
        position.setText(pos);
        switch (structure.getType())
        {
            case 0:
                type.setText("Road");
                break;
            case 1:
                type.setText("Residential");
                break;
            case 2:
                type.setText("Commercial");
                break;
        }

        PopupWindow puw = new PopupWindow(window, -2, -2);
        puw.setFocusable(true);
        puw.showAtLocation(rv, Gravity.TOP, 0, 128);

        ownerName.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                //this page has been left intentionally blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                //this page was left blank accidentally
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                dataElem.setOwnerName(s.toString());
                GameData.getInstance().updateMapElement(dataElem);
            }
        });

        demolish.setOnClickListener((z) -> {
            GameData.getInstance().demolish(dataElem.getStructure());
            dataElem.setStructure(null);
            rv.getAdapter().notifyItemChanged(mapPos);
            puw.dismiss();
        });

        addPhoto.setOnClickListener((z) -> {
            puw.dismiss();
            Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(photoIntent, PHOTO_REQUEST_CODE);
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultIntent)
    {
        if (resultCode == Activity.RESULT_OK && requestCode == PHOTO_REQUEST_CODE)
        {
            selElem.setImage((Bitmap) resultIntent.getExtras().get("data"));
        }
    }

}
