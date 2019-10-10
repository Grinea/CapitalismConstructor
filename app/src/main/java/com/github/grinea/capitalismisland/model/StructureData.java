package com.github.grinea.capitalismisland.model;

import com.github.grinea.capitalismisland.R;

public class StructureData
{
    private static StructureData instance = null;
    private Residential[] residential;
    private Commercial[] commercial;
    private Road[] road;

    private StructureData()
    {
        residential = new Residential[4];
        residential[0] = new Residential(R.drawable.ic_building1);
        residential[1] = new Residential(R.drawable.ic_building2);
        residential[2] = new Residential(R.drawable.ic_building3);
        residential[3] = new Residential(R.drawable.ic_building4);

        commercial = new Commercial[4];
        commercial[0] = new Commercial(R.drawable.ic_building5);
        commercial[1] = new Commercial(R.drawable.ic_building6);
        commercial[2] = new Commercial(R.drawable.ic_building7);
        commercial[3] = new Commercial(R.drawable.ic_building8);

        road = new Road[15];
        road[0] = new Road(R.drawable.ic_road_e);
        road[1] = new Road(R.drawable.ic_road_ew);
        road[2] = new Road(R.drawable.ic_road_n);
        road[3] = new Road(R.drawable.ic_road_ne);
        road[4] = new Road(R.drawable.ic_road_new);
        road[5] = new Road(R.drawable.ic_road_ns);
        road[6] = new Road(R.drawable.ic_road_nse);
        road[7] = new Road(R.drawable.ic_road_nsew);
        road[8] = new Road(R.drawable.ic_road_nsw);
        road[9] = new Road(R.drawable.ic_road_nw);
        road[10] = new Road(R.drawable.ic_road_s);
        road[11] = new Road(R.drawable.ic_road_se);
        road[12] = new Road(R.drawable.ic_road_sew);
        road[13] = new Road(R.drawable.ic_road_sw);
        road[14] = new Road(R.drawable.ic_road_w);

        instance = this;
    }

    public static StructureData getInstance()
    {
        if (instance == null)
        {
            instance = new StructureData();
        }

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

        if (position >= resLen + comLen + roaLen)
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
