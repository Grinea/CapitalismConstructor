package com.github.grinea.capitalismisland.model;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.github.grinea.capitalismisland.model.DatabaseSchema.SettingsTable;
import com.github.grinea.capitalismisland.model.DatabaseSchema.StateTable;
import com.github.grinea.capitalismisland.model.DatabaseSchema.MapTable;


public class GameDatabase extends SQLiteOpenHelper
{
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "capitalism.db";
    private SQLiteDatabase db;


    public GameDatabase(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + SettingsTable.NAME + "(" +
                SettingsTable.Cols.HEIGHT + " INTEGER DEFAULT 10, " +
                SettingsTable.Cols.WIDTH + " INTEGER DEFAULT 50, " +
                SettingsTable.Cols.INIT_CASH + " INTEGER DEFAULT 1000)");

        db.execSQL("CREATE TABLE " + StateTable.NAME + "(" +
                StateTable.Cols.TIME + " INTEGER, " +
                StateTable.Cols.MONEY + " INTEGER, " +
                StateTable.Cols.RES + " INTEGER, " +
                StateTable.Cols.COM + " INTEGER, " +
                StateTable.Cols.INCOME + " INTEGER)");

        db.execSQL("CREATE TABLE " + MapTable.NAME + "(" +
                MapTable.Cols.POS + " INTEGER, " +
                MapTable.Cols.STRUCTURE + " INTEGER, " +
                MapTable.Cols.OWNED + " INTEGER, " +
                MapTable.Cols.GRASS + " INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase newDb, int oldVersion, int newVersion)
    {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void setDB(SQLiteDatabase db)
    {
        this.db = db;
    }

    public void updateMapElement(MapElement mapEl)
    {
        ContentValues cv = new ContentValues();
        cv.put(MapTable.Cols.STRUCTURE, mapEl.getStructure().getID());
        cv.put(MapTable.Cols.OWNED, mapEl.getOwnerName());
        String[] pos = {String.valueOf(mapEl.getPos())};
        db.update(MapTable.NAME, cv, "position = ?", pos);
    }

    public void insertMapElement(MapElement mapEl)
    {
        ContentValues cv = new ContentValues();
        if (mapEl.getStructure() != null)
        {
            cv.put(MapTable.Cols.STRUCTURE, mapEl.getStructure().getID());
        }
        else
        {
            cv.put(MapTable.Cols.STRUCTURE, -1);
        }
        cv.put(MapTable.Cols.OWNED, mapEl.getOwnerName());
        cv.put(MapTable.Cols.POS, mapEl.getPos());
        cv.put(MapTable.Cols.GRASS, mapEl.getGrassType());
        db.insert(MapTable.NAME, null, cv);
    }

    public void setSettings(int height, int width, int cash)
    {
        db.delete(SettingsTable.NAME, null, null);
        ContentValues cv = new ContentValues();
        cv.put(SettingsTable.Cols.HEIGHT, height);
        cv.put(SettingsTable.Cols.WIDTH, width);
        cv.put(SettingsTable.Cols.INIT_CASH, cash);
        db.insert(SettingsTable.NAME, null, cv);
    }

    public void setState(int gameTime, int money, int nRes, int nCom, int income)
    {
        db.delete(StateTable.NAME, null, null);
        ContentValues cv = new ContentValues();
        cv.put(StateTable.Cols.TIME, gameTime);
        cv.put(StateTable.Cols.MONEY, money);
        cv.put(StateTable.Cols.RES, nRes);
        cv.put(StateTable.Cols.COM, nCom);
        cv.put(StateTable.Cols.INCOME, income);
        db.insert(StateTable.NAME, null, cv);
    }

    public void reset()
    {
        db.delete(StateTable.NAME, null, null);
        db.delete(MapTable.NAME, null, null);
    }

    public void updateSettings(Settings sts)
    {
        Cursor cur = db.query(SettingsTable.NAME,null,null,null,null,null,null);
        sts.setMapWidth(cur.getInt(cur.getColumnIndex(SettingsTable.Cols.WIDTH)));
        sts.setMapHeight(cur.getInt(cur.getColumnIndex(SettingsTable.Cols.HEIGHT)));
        sts.setInitialMoney(cur.getInt(cur.getColumnIndex(SettingsTable.Cols.INIT_CASH)));
        cur.close();
    }

    public SettingsCursor settingsQuery()
    {
        return new SettingsCursor(db.query(SettingsTable.NAME, null, null, null, null, null, null));
    }

    public MapCursor mapQuery()
    {
        return new MapCursor(db.query(MapTable.NAME,null,null,null,null,null,null));
    }

    public StateCursor stateQuery()
    {
        return new StateCursor(db.query(StateTable.NAME, null, null, null, null, null, null));
    }

}
