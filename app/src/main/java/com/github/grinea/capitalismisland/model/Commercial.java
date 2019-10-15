package com.github.grinea.capitalismisland.model;

public class Commercial extends Structure
{
    Commercial(int imageID, int ID)
    {
        super(imageID, ID);
    }

    @Override
    public int getType()
    {
        return 2;
    }

    @Override
    public int getCost()
    {
        return GameData.getInstance().getSettings().getCommCost();
    }
}
