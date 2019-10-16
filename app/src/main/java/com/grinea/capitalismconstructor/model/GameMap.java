package com.grinea.capitalismconstructor.model;

/*
* Wrapper class for the 2D array to ease use with a recycler view that works
* on a linear position
*/

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
                map[ii][jj]  = new MapElement(ii*map[0].length + jj);
            }
        }
    }

    public MapElement getElement(int position)
    {
        //out of bounds check
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

    //Decides if a cell is clear and adjacent to a road
    public boolean isBuildable(int position)
    {
        boolean buildable = false;

        int row = position / map[0].length;
        int col = position % map[0].length;

        //cell is clear
        if (map[row][col].getStructure() != null)
        {
            return false;
        }

        //checks N if N exists
        if (row > 0)
        {
            if (map[row - 1][col].getStructureType() == 0)
            {
                buildable = true;
            }
        }

        //checks S if S exists
        if (row < map.length - 1)
        {
            if (map[row + 1][col].getStructureType() == 0)
            {
                buildable = true;
            }
        }

        //checks W if W exists
        if (col > 0)
        {
            if (map[row][col - 1].getStructureType() == 0)
            {
                buildable = true;
            }
        }

        //checks E if E exists
        if (col < map[0].length - 1)
        {
            if (map[row][col + 1].getStructureType() == 0)
            {
                buildable = true;
            }
        }

        return buildable;
    }

    public void setElement(MapElement elem)
    {
        map[elem.getPos() / map[0].length][elem.getPos() % map[0].length] = elem;
    }
}
