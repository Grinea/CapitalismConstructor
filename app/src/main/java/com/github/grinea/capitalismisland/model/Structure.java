package com.github.grinea.capitalismisland.model;

public abstract class Structure
{

    int imageID;
    int ID;

    public Structure(int imageID, int ID)
    {
        this.ID = ID;
        this.imageID = imageID;
    }

    public int getID()
    {
        return ID;
    }

    public int getImageID() {
        return imageID;
    }

    public abstract int getCost();

    public abstract int getType();
}
