package com.grinea.capitalismconstructor.model;

//Class representing houses, defines return values

public class Residential extends Structure
{
    public Residential(int imageID, int ID)
    {
        super(imageID, ID);
    }

    @Override
    public int getType()
    {
        return 1;
    }

    @Override
    public int getCost()
    {
        return GameData.getInstance().getSettings().getHouseCost();
    }

    @Override
    public String getDefaultName()
    {
        return "Residential";
    }

}
