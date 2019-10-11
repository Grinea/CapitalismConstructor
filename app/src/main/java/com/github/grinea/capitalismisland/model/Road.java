package com.github.grinea.capitalismisland.model;

public class Road extends Structure
{
    public Road(int imageID)
    {
        super(imageID);
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
}
