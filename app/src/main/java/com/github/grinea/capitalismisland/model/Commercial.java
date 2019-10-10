package com.github.grinea.capitalismisland.model;

public class Commercial extends Structure
{
    public Commercial(int imageID)
    {
        super(imageID);
    }

    @Override
    public int getType()
    {
        return 2;
    }
}
