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

        return map[position%map.length][position/map.length];

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

    public boolean isBuildable(int position) {
        boolean buildable = false;

        int row = position / map.length;
        int col = position % map.length;

        if (row > 0)
        {
            if (map[col][row - 1].getStructureType() == 0)
            {
                buildable = true;
            }
        }

        if (row < map.length - 1)
        {
            if (map[col][row + 1].getStructureType() == 0)
            {
                buildable = true;
            }
        }

        if (col > 0)
        {
            if (map[col-1][row].getStructureType() == 0)
            {
                buildable = true;
            }
        }

        if (col < map[0].length - 1)
        {
            if (map[col + 1][row].getStructureType() == 0)
            {
                buildable = true;
            }
        }

        return buildable;
    }
}
