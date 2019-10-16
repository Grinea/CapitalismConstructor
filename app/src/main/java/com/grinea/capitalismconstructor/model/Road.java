package com.grinea.capitalismconstructor.model;

//Class representing roads, defines return values

public class Road extends Structure
{
    public Road(int imageID, int ID)
    {
        super(imageID, ID);
    }

    @Override
    public int getType()
    {
        return 0;
    }

    @Override
    public int getCost()
    {
        return GameData.getInstance().getSettings().getRoadCost();
    }

    @Override
    public String getDefaultName()
    {
        return "Road";
    }
}
