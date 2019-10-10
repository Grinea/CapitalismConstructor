package com.github.grinea.capitalismisland.model;

import android.graphics.Bitmap;

import java.util.Random;

public class MapElement
{
    private Structure structure;
    private Bitmap image;
    private String ownerName;
    private int grassType;

    public MapElement(Structure structure, Bitmap image, String ownerName, int grassType)
    {
        this.structure = structure;
        this.image = image;
        this.ownerName = ownerName;
        this.grassType = grassType;
    }

    public MapElement()
    {
        Random r = new Random();
        this.structure = null;
        this.image = null;
        this.ownerName = "Dave";
        this.grassType = r.nextInt((4-1)+1)+1;
    }

    public int getGrassType()
    {
        return grassType;
    }

    public Structure getStructure()
    {
        return structure;
    }

    public void setStructure(Structure structure)
    {
        this.structure = structure;
    }

    public int getStructureType()
    {
        return structure.getType();
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
