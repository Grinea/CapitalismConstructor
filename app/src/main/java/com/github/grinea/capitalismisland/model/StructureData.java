package com.github.grinea.capitalismisland.model;

public class StructureData
{
    private static StructureData instance = null;
    private Residential[] residential;
    private Commercial[] commercial;
    private Road[] road;

    private StructureData()
    {
        //yeet
    }

    public static StructureData getInstance()
    {
        return instance;
    }
}
