package com.github.grinea.capitalismisland.model;

public class GameMap
{
    MapElement[][] map;

    public GameMap(int rows, int cols)
    {
        this.map = new MapElement[rows][cols];
        for (int ii = 0; ii < map.length; ii++)
        {
            for (int jj = 0; jj < map[0].length; jj++)
            {
                map[ii][jj]  = new MapElement();
            }
        }
    }

    public MapElement getElement(int position)
    {
        MapElement ret = null;

        if (position > (map[0].length * map.length)-1)
        {
            throw new IllegalArgumentException("No such map element.");
        }

        return map[position/map[0].length][position%map[0].length];

    }

    public int getCount()
    {
        return map[0].length * map.length;
    }

    public int getRows()
    {
        return map.length;
    }

    public int getCols()
    {
        return map[0].length;
    }
}
