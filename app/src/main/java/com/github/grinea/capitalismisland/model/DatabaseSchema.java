package com.github.grinea.capitalismisland.model;

public class DatabaseSchema
{
    public static class SettingsTable
    {
        public static final String NAME = "settings";
        public static class Cols
        {
            public static final String WIDTH = "mapWidth";
            public static final String HEIGHT = "mapHeight";
            public static final String INIT_CASH = "initialMoney";
        }
    }

    public static class StateTable
    {
        public static final String NAME = "gameState";
        public static class Cols
        {
            public static final String TIME = "gameTime";
            public static final String MONEY = "money";
            public static final String RES = "nRes";
            public static final String COM = "nCom";
            public static final String INCOME = "lastIncome";
        }
    }

    public static class MapTable
    {
        public static final String NAME = "mapElements";
        public static class Cols
        {
            public static final String POS = "position";
            public static final String STRUCTURE = "structure";
            public static final String OWNED = "ownerName";
            public static final String GRASS = "grassType";
        }
    }

}
