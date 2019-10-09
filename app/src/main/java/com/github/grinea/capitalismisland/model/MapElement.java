package com.github.grinea.capitalismisland.model;

import android.graphics.Bitmap;

public class MapElement
{
    private Structure structure;
    private Bitmap image;
    private String ownerName;

    public Structure getStructure()
    {
        return structure;
    }

    public void setStructure(Structure structure)
    {
        this.structure = structure;
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
