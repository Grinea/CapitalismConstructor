package com.grinea.capitalismconstructor.model;

/*
* database wrapper class that contains the SQLite database and provides wrapper
* methods for inserting and updating the data within in a manner easier for use
* in the game
*/
import android.content.ContentValues;
import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class GameDatabase extends SQLiteOpenHelper
{
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "capitalism.db";
    private SQLiteDatabase db;


    public GameDatabase(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
        this.db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + DatabaseSchema.SettingsTable.NAME + "(" +
                DatabaseSchema.SettingsTable.Cols.HEIGHT + " INTEGER DEFAULT 10, " +
                DatabaseSchema.SettingsTable.Cols.WIDTH + " INTEGER DEFAULT 50, " +
                DatabaseSchema.SettingsTable.Cols.INIT_CASH + " INTEGER DEFAULT 1000)");

        db.execSQL("CREATE TABLE " + DatabaseSchema.StateTable.NAME + "(" +
                DatabaseSchema.StateTable.Cols.TIME + " INTEGER, " +
                DatabaseSchema.StateTable.Cols.MONEY + " INTEGER, " +
                DatabaseSchema.StateTable.Cols.RES + " INTEGER, " +
                DatabaseSchema.StateTable.Cols.COM + " INTEGER, " +
                DatabaseSchema.StateTable.Cols.INCOME + " INTEGER)");

        db.execSQL("CREATE TABLE " + DatabaseSchema.MapTable.NAME + "(" +
                DatabaseSchema.MapTable.Cols.POS + " INTEGER, " +
                DatabaseSchema.MapTable.Cols.STRUCTURE + " INTEGER, " +
                DatabaseSchema.MapTable.Cols.OWNED + " INTEGER, " +
                DatabaseSchema.MapTable.Cols.GRASS + " INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase newDb, int oldVersion, int newVersion)
    {
        throw new UnsupportedOperationException("Not implemented");
    }


    //Overwrite the owner and structure of a mapelement in the database
    void updateMapElement(MapElement mapEl)
    {
        ContentValues cv = new ContentValues();
        if (mapEl.getStructure() != null)
        {
            cv.put(DatabaseSchema.MapTable.Cols.STRUCTURE, mapEl.getStructure().getID());
        }
        else
        {
            cv.put(DatabaseSchema.MapTable.Cols.STRUCTURE, -1);
        }
        cv.put(DatabaseSchema.MapTable.Cols.OWNED, mapEl.getOwnerName());
        String[] pos = {String.valueOf(mapEl.getPos())};
        db.update(DatabaseSchema.MapTable.NAME, cv, "position = ?", pos);
    }

    //Inserting a mapelement for after database has been cleared by reset
    void insertMapElement(MapElement mapEl)
    {
        ContentValues cv = new ContentValues();
        if (mapEl.getStructure() != null)
        {
            cv.put(DatabaseSchema.MapTable.Cols.STRUCTURE, mapEl.getStructure().getID());
        }
        else
        {
            cv.put(DatabaseSchema.MapTable.Cols.STRUCTURE, -1);
        }
        cv.put(DatabaseSchema.MapTable.Cols.OWNED, mapEl.getOwnerName());
        cv.put(DatabaseSchema.MapTable.Cols.POS, mapEl.getPos());
        cv.put(DatabaseSchema.MapTable.Cols.GRASS, mapEl.getGrassType());
        db.insert(DatabaseSchema.MapTable.NAME, null, cv);
    }

    //Save values of settings
    void setSettings(int height, int width, int cash)
    {
        db.delete(DatabaseSchema.SettingsTable.NAME, null, null);
        ContentValues cv = new ContentValues();
        cv.put(DatabaseSchema.SettingsTable.Cols.HEIGHT, height);
        cv.put(DatabaseSchema.SettingsTable.Cols.WIDTH, width);
        cv.put(DatabaseSchema.SettingsTable.Cols.INIT_CASH, cash);
        db.insert(DatabaseSchema.SettingsTable.NAME, null, cv);
    }

    //Save values of state
    void setState(int gameTime, int money, int nRes, int nCom, int income)
    {
        db.delete(DatabaseSchema.StateTable.NAME, null, null);
        ContentValues cv = new ContentValues();
        cv.put(DatabaseSchema.StateTable.Cols.TIME, gameTime);
        cv.put(DatabaseSchema.StateTable.Cols.MONEY, money);
        cv.put(DatabaseSchema.StateTable.Cols.RES, nRes);
        cv.put(DatabaseSchema.StateTable.Cols.COM, nCom);
        cv.put(DatabaseSchema.StateTable.Cols.INCOME, income);
        db.insert(DatabaseSchema.StateTable.NAME, null, cv);
    }

    //Clears the state and map for resetting the game
    void reset()
    {
        db.delete(DatabaseSchema.StateTable.NAME, null, null);
        db.delete(DatabaseSchema.MapTable.NAME, null, null);
    }

    //Returns a settings cursor for accessing the saved settings
    SettingsCursor settingsQuery()
    {
        return new SettingsCursor(db.query(DatabaseSchema.SettingsTable.NAME, null, null, null, null, null, null));
    }

    //returns a mapcursor for accessing saved mapelements
    MapCursor mapQuery()
    {
        return new MapCursor(db.query(DatabaseSchema.MapTable.NAME,null,null,null,null,null,null));
    }

    //returns a statequery for accessing saved gamestate data
    StateCursor stateQuery()
    {
        return new StateCursor(db.query(DatabaseSchema.StateTable.NAME, null, null, null, null, null, null));
    }

}
