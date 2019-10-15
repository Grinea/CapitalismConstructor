package com.grinea.capitalismconstructor.model;

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

    public String getDefaultName()
    {
        String name = "Default";
        switch (getType())
        {

            case 0:
                name = "Road";
            break;
            case 1:
                name = "Residential";
            break;
            case 2:
                name = "Commercial";
            break;
        }
        return name;
    }
}
