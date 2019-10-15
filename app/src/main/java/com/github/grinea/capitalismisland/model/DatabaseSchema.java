package com.github.grinea.capitalismisland.model;

public class DatabaseSchema
{

    static class SettingsTable
    {
        static final String NAME = "settings";
        static class Cols
        {
            static final String WIDTH = "mapWidth";
            static final String HEIGHT = "mapHeight";
            static final String INIT_CASH = "initialMoney";
        }
    }

    static class StateTable
    {
        static final String NAME = "gameState";
        static class Cols
        {
            static final String TIME = "gameTime";
            static final String MONEY = "money";
            static final String RES = "nRes";
            static final String COM = "nCom";
            static final String INCOME = "lastIncome";
        }
    }

    static class MapTable
    {
        static final String NAME = "mapElements";
        static class Cols
        {
            static final String POS = "position";
            static final String STRUCTURE = "structure";
            static final String OWNED = "ownerName";
            static final String GRASS = "grassType";
        }
    }

}
