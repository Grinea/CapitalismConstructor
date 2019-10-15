package com.grinea.capitalismconstructor.model;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.grinea.capitalismconstructor.R;

import java.util.Random;

public class MapElement
{
    private static int[] grasses = {R.drawable.ic_grass1,
                                    R.drawable.ic_grass2,
                                    R.drawable.ic_grass3,
                                    R.drawable.ic_grass4};
    private Structure structure;
    private Bitmap image;
    private String ownerName;
    private int grassType;
    private int position;

    public MapElement(int position, int structure, String ownerName, int grassType)
    {
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

    public MapElement(int position)
    {
        Random r = new Random();
        this.structure = null;
        this.image = null;
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

    public int getPos()
    {
        return position;
    }

    public void setStructure(Structure structure)
    {
        this.structure = structure;
    }

    public int getStructureType()
    {
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

    @NonNull
    @Override
    public String toString()
    {
        return "Map element at " + position + " Structure type: " + structure;
    }
}
