package com.github.grinea.capitalismisland.model;

public class Settings
{
    private int mapWidth = 50;
    private int mapHeight = 10;
    private int initialMoney = 1000;
    private int familySize = 4;
    private int shopSize = 6;
    private int salary = 10;
    private double taxRate = 0.3;
    private int serviceCost = 2;
    private int houseCost = 100;
    private int commCost = 500;
    private int roadCost = 20;

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

    public int getFamilySize()
    {
        return familySize;
    }

    public void setFamilySize(int familySize)
    {
        this.familySize = familySize;
    }

    public int getShopSize()
    {
        return shopSize;
    }

    public void setShopSize(int shopSize)
    {
        this.shopSize = shopSize;
    }

    public int getSalary()
    {
        return salary;
    }

    public void setSalary(int salary)
    {
        this.salary = salary;
    }

    public double getTaxRate()
    {
        return taxRate;
    }

    public void setTaxRate(double taxRate)
    {
        this.taxRate = taxRate;
    }

    public int getServiceCost()
    {
        return serviceCost;
    }

    public void setServiceCost(int serviceCost)
    {
        this.serviceCost = serviceCost;
    }

    public int getHouseCost()
    {
        return houseCost;
    }

    public void setHouseCost(int houseCost)
    {
        this.houseCost = houseCost;
    }

    public int getCommCost()
    {
        return commCost;
    }

    public void setCommCost(int commCost)
    {
        this.commCost = commCost;
    }

    public int getRoadCost()
    {
        return roadCost;
    }

    public void setRoadCost(int roadCost)
    {
        this.roadCost = roadCost;
    }
}
