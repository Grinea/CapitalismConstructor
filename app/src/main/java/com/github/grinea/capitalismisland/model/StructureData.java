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

    public int getCount()
    {
        return residential.length + commercial.length + road.length;
    }

    public Structure getElement(int position)
    {
        int resLen = residential.length;
        int comLen = commercial.length;
        int roaLen = road.length;

        if (position >= resLen + comLen + roaLen - 1)
        {
            throw new IllegalArgumentException("No such structure");
        }

        if (position < resLen)
        {
            return residential[position];
        }
        else if(position < resLen + comLen)
        {
            return commercial[position - resLen];
        }
        else
        {
            return road[position - resLen - comLen];
        }
    }
}
