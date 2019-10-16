package com.grinea.capitalismconstructor.view;

/*
* Class that represents the 2D map of the game area.
*/

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

import com.grinea.capitalismconstructor.R;
import com.grinea.capitalismconstructor.model.GameData;
import com.grinea.capitalismconstructor.model.GameMap;
import com.grinea.capitalismconstructor.model.MapElement;
import com.grinea.capitalismconstructor.model.Structure;


public class MapFragment extends Fragment
{
    private static final int PHOTO_REQUEST_CODE = 1;
    private MapElement selElem;

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
        GameMap map = GameData.getInstance().getMap();
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        //create and configure recyclerview
        rv = view.findViewById(R.id.mapRecycler);
        rv.setLayoutManager(new GridLayoutManager(getActivity(), map.getRows(),
                                                  GridLayoutManager.HORIZONTAL,
                                                  false));
        MapAdapter adapter = new MapAdapter(getContext(), map);
        rv.setAdapter(adapter);

        return view;
    }

    //Map's adapter class
    private class MapAdapter extends RecyclerView.Adapter<MapViewHolder>
    {
        private GameMap src;
        private LayoutInflater li;

        MapAdapter(Context context, GameMap src)
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
        public MapViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                int viewType)
        {
            return new MapViewHolder(li, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull MapViewHolder holder,
                                     int position)
        {
            holder.bind(src.getElement(position));
        }
    }

    //Map's viewholder
    private class MapViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView structImg;
        private ImageView background;
        private MapElement data;

        MapViewHolder(LayoutInflater li, ViewGroup parent)
        {
            super(li.inflate(R.layout.fragment_map_element, parent, false));

            //sets size of each map cell to be height of fragment / rows
            int size =
                    parent.getMeasuredHeight() / GameData.getInstance().getMap()
                                                         .getRows() + 1;
            ViewGroup.LayoutParams lp = itemView.getLayoutParams();
            lp.width = size;
            lp.height = size;

            //collect references
            structImg = itemView.findViewById(R.id.structure);
            background = itemView.findViewById(R.id.background);

            //OCL for view
            itemView.setOnClickListener((v) -> {

                //Sets reference field to selected element
                selElem = data;

                //collect references
                FragmentManager fm = getFragmentManager();
                SelectorFragment sf = (SelectorFragment) fm.findFragmentById(R.id.selector);
                Structure selStruct = sf.getSelStruct();

                //if nothing is selected aka inspectmode
                if (selStruct == null)
                {
                    //ignore grass only
                    if (data.getStructure() != null)
                    {
                        inspectPopUp(getAdapterPosition());
                    }
                } else //if something is selected aka build mode
                {
                    //check if it is possible to build and if so build
                    if (((GameFragment) fm
                            .findFragmentById(R.id.fragment_holder))
                            .build(selStruct, getAdapterPosition()))
                    {
                        //reset selector and update mapelement
                        sf.clearSelStruct();

                        //update mapelement and image display
                        data.setOwnerName(selStruct.getDefaultName());
                        structImg.setImageResource(selStruct.getImageID());

                        //save changes to db
                        GameData.getInstance().updateMapElement(data);
                    }
                }
            });
        }

        public void bind(MapElement data)
        {
            this.data = data;

            //set grass
            background.setImageResource(data.getGrassType());

            //loads image/structure picture/nothing as appropriate
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
        MapElement dataElem =
                GameData.getInstance().getMap().getElement(mapPos);
        Structure structure = dataElem.getStructure();
        View window = getLayoutInflater().inflate(R.layout.popup_inspect, null);

        //collect references
        ImageView picture = window.findViewById(R.id.picture);
        EditText ownerName = window.findViewById(R.id.owner_name);
        TextView position = window.findViewById(R.id.position);
        TextView type = window.findViewById(R.id.structure_type);

        Button demolish = window.findViewById(R.id.demolish);
        Button addPhoto = window.findViewById(R.id.add_photo);

        picture.setImageResource(structure.getImageID());
        ownerName.setText(dataElem.getOwnerName());


        //coordinate calculation
        int cols = GameData.getInstance().getMap().getCols();
        String coords = "X: " + (mapPos / cols) + " Y: " + (mapPos % cols);
        position.setText(coords);

        type.setText(structure.getDefaultName());

        //pop up window instantiation
        PopupWindow puw = new PopupWindow(window, -2, -2);
        puw.setFocusable(true);
        puw.showAtLocation(rv, Gravity.TOP, 0, 128);

        //TCL for owner name being changed
        ownerName.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after)
            {
                //this page has been left intentionally blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count)
            {
                //this page was left blank accidentally
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                //update name in element and database
                dataElem.setOwnerName(s.toString());
                GameData.getInstance().updateMapElement(dataElem);
            }
        });

        //OCL for demolish button, updates element, gamestate and adapter
        demolish.setOnClickListener((z) -> {
            GameData.getInstance().demolish(dataElem.getStructure());
            dataElem.setStructure(null);
            GameData.getInstance().updateMapElement(dataElem);
            rv.getAdapter().notifyItemChanged(mapPos);

            puw.dismiss();
        });

        //OCL for adding thumbnail photos
        addPhoto.setOnClickListener((z) -> {
            puw.dismiss();
            Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(photoIntent, PHOTO_REQUEST_CODE);
        });
    }

    //result for photo OCL
    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultIntent)
    {
        if (resultCode == Activity.RESULT_OK && requestCode == PHOTO_REQUEST_CODE)
        {
            selElem.setImage((Bitmap) resultIntent.getExtras().get("data"));
        }
    }

}
