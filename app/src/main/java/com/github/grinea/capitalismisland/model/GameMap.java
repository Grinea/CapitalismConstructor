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
        return map[0].length;
    }

    public int getCols()
    {
        return map[0].length;
    }

    public boolean isBuildable(int position)
    {
        boolean buildable = false;

        int row = position / map[0].length;
        int col = position % map[0].length;

        if (map[row][col].getStructure() != null)
        {
            return false;
        }

        if (row > 0)
        {
            if (map[row - 1][col].getStructureType() == 0)
            {
                buildable = true;
            }
        }

        if (row < map.length - 1)
        {
            if (map[row + 1][col].getStructureType() == 0)
            {
                buildable = true;
            }
        }

        if (col > 0)
        {
            if (map[row][col - 1].getStructureType() == 0)
            {
                buildable = true;
            }
        }

        if (col < map[0].length - 1)
        {
            if (map[row][col + 1].getStructureType() == 0)
            {
                buildable = true;
            }
        }

        return buildable;
    }
}
