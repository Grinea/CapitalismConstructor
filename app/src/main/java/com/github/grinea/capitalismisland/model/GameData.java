package com.github.grinea.capitalismisland.model;

public class GameData {

    private static GameData instance = null;
    private Settings settings;
    private GameMap map;
    private int money;
    private int gameTime;

    private GameData()
    {
        settings = new Settings();
        map = new GameMap(10,20);
        money = 0;
        gameTime = 0;
    }

    public static GameData getInstance()
    {
        if (instance == null)
        {
            instance = new GameData();
        }

        return instance;
    }

    public GameMap getMap()
    {
        return map;
    }

    public int getMoney()
    {
        return money;
    }

    public void setMoney(int money)
    {
        this.money = money;
    }

    public int getGameTime()
    {
        return gameTime;
    }

    public void setGameTime(int gameTime)
    {
        this.gameTime = gameTime;
    }

    public Settings getSettings() {
        return settings;
    }
}
