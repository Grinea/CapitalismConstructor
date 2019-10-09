package com.github.grinea.capitalismisland.model;

public class GameData {

    private static GameData instance = null;
    private Settings settings;
    private MapElement[][] map;
    private int money;
    private int gameTime;

    private GameData()
    {
        settings = new Settings();
        map = new MapElement[30][10];
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

}
