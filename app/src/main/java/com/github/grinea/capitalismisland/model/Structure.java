package com.github.grinea.capitalismisland.model;

public abstract class Structure
{
    int imageID;

    public Structure(int imageID)
    {
        this.imageID = imageID;
    }

    public int getImageID() {
        return imageID;
    }

    public abstract int getType();
}
