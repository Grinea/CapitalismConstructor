package com.github.grinea.capitalismisland.model;

import android.graphics.Bitmap;

import java.util.Random;

public class MapElement
{
    private Structure structure;
    private Bitmap image;
    private String ownerName;
    private int grassType;
    private int position;

    public MapElement(int position, Structure structure, String ownerName, int grassType)
    {
        this.structure = structure;
        this.ownerName = ownerName;
        this.grassType = grassType;
        this.position = position;
    }

    public MapElement(int position)
    {
        Random r = new Random();
        this.structure = null;
        this.image = null;
        this.ownerName = "Dave";
        this.grassType = r.nextInt((4-1)+1)+1;
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
}
