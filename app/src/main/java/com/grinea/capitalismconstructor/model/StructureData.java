package com.grinea.capitalismconstructor.model;

/*
* Singleton class respresenting all the structures available for building
*/

import com.grinea.capitalismconstructor.R;

public class StructureData
{
    private static StructureData instance = null;
    private Residential[] residential;
    private Commercial[] commercial;
    private Road[] road;

    private StructureData()
    {
        residential = new Residential[4];
        residential[0] = new Residential(R.drawable.ic_building1, 0);
        residential[1] = new Residential(R.drawable.ic_building2, 1);
        residential[2] = new Residential(R.drawable.ic_building3, 2);
        residential[3] = new Residential(R.drawable.ic_building4, 3);

        commercial = new Commercial[4];
        commercial[0] = new Commercial(R.drawable.ic_building5, 4);
        commercial[1] = new Commercial(R.drawable.ic_building6, 5);
        commercial[2] = new Commercial(R.drawable.ic_building7, 6);
        commercial[3] = new Commercial(R.drawable.ic_building8, 7);

        road = new Road[15];
        road[0] = new Road(R.drawable.ic_road_e, 8);
        road[1] = new Road(R.drawable.ic_road_ew, 9);
        road[2] = new Road(R.drawable.ic_road_n, 10);
        road[3] = new Road(R.drawable.ic_road_ne, 11);
        road[4] = new Road(R.drawable.ic_road_new, 12);
        road[5] = new Road(R.drawable.ic_road_ns, 13);
        road[6] = new Road(R.drawable.ic_road_nse, 14);
        road[7] = new Road(R.drawable.ic_road_nsew, 15);
        road[8] = new Road(R.drawable.ic_road_nsw, 16);
        road[9] = new Road(R.drawable.ic_road_nw, 17);
        road[10] = new Road(R.drawable.ic_road_s, 18);
        road[11] = new Road(R.drawable.ic_road_se, 19);
        road[12] = new Road(R.drawable.ic_road_sew, 20);
        road[13] = new Road(R.drawable.ic_road_sw, 21);
        road[14] = new Road(R.drawable.ic_road_w, 22);

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

    //Converts position number to structure object based on location in arrays
    public Structure getElement(int position)
    {
        int resLen = residential.length;
        int comLen = commercial.length;
        int roaLen = road.length;

        if (position >= resLen + comLen + roaLen)
        {
            throw new IllegalArgumentException("No such structure");
        }

        //position breakdown
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
