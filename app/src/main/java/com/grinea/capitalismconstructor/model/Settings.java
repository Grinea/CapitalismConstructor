package com.grinea.capitalismconstructor.model;

//Settings class, contains game variables

public class Settings
{
    private int mapWidth;
    private int mapHeight;
    private int initialMoney;
    private int familySize;
    private int shopSize;
    private int salary;
    private double taxRate;
    private int serviceCost;
    private int houseCost;
    private int commCost;
    private int roadCost;

    public Settings()
    {
        mapWidth = 50;
        mapHeight = 10;
        initialMoney = 1000;
        familySize = 4;
        shopSize = 6;
        salary = 10;
        taxRate = 0.3;
        serviceCost = 2;
        houseCost = 100;
        commCost = 500;
        roadCost = 20;
    }

    public int getMapWidth()
    {
        return mapWidth;
    }

    public void setMapWidth(int mapWidth)
    {
        this.mapWidth = mapWidth;
    }

    public int getMapHeight()
    {
        return mapHeight;
    }

    public void setMapHeight(int mapHeight)
    {
        this.mapHeight = mapHeight;
    }

    public int getInitialMoney()
    {
        return initialMoney;
    }

    public void setInitialMoney(int initialMoney)
    {
        this.initialMoney = initialMoney;
    }

    int getFamilySize()
    {
        return familySize;
    }


    int getShopSize()
    {
        return shopSize;
    }


    int getSalary()
    {
        return salary;
    }


    double getTaxRate()
    {
        return taxRate;
    }


    int getServiceCost()
    {
        return serviceCost;
    }


    public int getHouseCost()
    {
        return houseCost;
    }


    public int getCommCost()
    {
        return commCost;
    }


    public int getRoadCost()
    {
        return roadCost;
    }

}
