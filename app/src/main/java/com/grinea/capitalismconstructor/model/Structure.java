package com.grinea.capitalismconstructor.model;

//Abstract class representing a built structure

public abstract class Structure
{

    private int imageID;
    private int ID;

    public Structure(int imageID, int ID)
    {
        this.ID = ID;
        this.imageID = imageID;
    }

    int getID()
    {
        return ID;
    }

    public int getImageID()
    {
        return imageID;
    }

    public abstract int getCost();

    public abstract int getType();

    public abstract String getDefaultName();
}
