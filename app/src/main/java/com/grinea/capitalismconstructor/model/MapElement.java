package com.grinea.capitalismconstructor.model;

//Class representing a single map cell, uses -1 to indicate nulls for database

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.grinea.capitalismconstructor.R;

import java.util.Random;

public class MapElement
{
    //available backgrounds
    private static int[] grasses = {R.drawable.ic_grass1,
                                    R.drawable.ic_grass2,
                                    R.drawable.ic_grass3,
                                    R.drawable.ic_grass4};
    private Structure structure;
    private Bitmap image;
    private String ownerName;
    private int grassType;
    private int position;

    //Alternate constructor used when rebuilding from database
    MapElement(int position, int structure, String ownerName, int grassType)
    {
        //blank structure
        if (structure != -1)
        {
            this.structure = StructureData.getInstance().getElement(structure);
        }
        else
        {
            this.structure = null;
        }
        this.ownerName = ownerName;
        this.grassType = grassType;
        this.position = position;
    }

    MapElement(int position)
    {
        Random r = new Random();
        this.structure = null;
        this.image = null;
        //randomly assign background for natural look
        this.grassType = grasses[r.nextInt(grasses.length)];
        this.position = position;
    }

    public int getGrassType()
    {
        return grassType;
    }

    public Structure getStructure()
    {
        return structure;
    }

    int getPos()
    {
        return position;
    }

    public void setStructure(Structure structure)
    {
        this.structure = structure;
    }

    int getStructureType()
    {
        //returns -1 on null for database saving
        if (structure == null)
        {
            return -1;
        }
        else
        {
            return structure.getType();
        }
    }

    public Bitmap getImage()
    {
        return image;
    }

    public void setImage(Bitmap image)
    {
        this.image = image;
    }

    public String getOwnerName()
    {
        return ownerName;
    }

    public void setOwnerName(String ownerName)
    {
        this.ownerName = ownerName;
    }

}
