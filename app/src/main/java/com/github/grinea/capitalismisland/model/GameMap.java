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

    public boolean isBuildable(int position)
    {
        boolean buildable = false;

        int row = position % map.length;
        int col = position / map.length;

        System.out.println("Column check:\n" + (col + 1) + "/" + (map[0].length));
        System.out.println("Row check:\n" + (row + 1) + "/" + (map.length));

        if (map[row][col].getStructure() != null)
        {
            System.out.println("Not empty");
            return false;
        }

        if (row > 0)
        {
            if (map[row - 1][col].getStructureType() == 0)
            {
                System.out.println("N:T");
                buildable = true;
            }
            else
            {
                System.out.println("N:F");
            }
        }

        if (row < map.length - 1)
        {
            if (map[row + 1][col].getStructureType() == 0)
            {
                System.out.println("S:T");
                buildable = true;
            }
            else
            {
                System.out.println("S:F");
            }
        }

        if (col > 0)
        {
            if (map[row][col - 1].getStructureType() == 0)
            {
                System.out.println("W:T");
                buildable = true;
            }
            else
            {
                System.out.println("W:F");
            }
        }

        if (col < map[0].length - 1)
        {
            if (map[row][col + 1].getStructureType() == 0)
            {
                System.out.println("E:T");
                buildable = true;
            }
            else
            {
                System.out.println("E:F");
            }
        }

        return buildable;
    }
}
