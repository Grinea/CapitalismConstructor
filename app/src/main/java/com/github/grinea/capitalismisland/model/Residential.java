package com.github.grinea.capitalismisland.model;

public class Residential extends Structure
{
    public Residential(int imageID)
    {
        super(imageID);
    }

    @Override
    public int getType()
    {
        return 1;
    }
}
