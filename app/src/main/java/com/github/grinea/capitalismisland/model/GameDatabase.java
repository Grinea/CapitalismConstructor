package com.github.grinea.capitalismisland.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.github.grinea.capitalismisland.model.DatabaseSchema.SettingsTable;
import com.github.grinea.capitalismisland.model.DatabaseSchema.StateTable;
import com.github.grinea.capitalismisland.model.DatabaseSchema.MapTable;


public class GameDatabase extends SQLiteOpenHelper
{
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "capitalism.db";

    public GameDatabase(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE " + SettingsTable.NAME + "(" +
                SettingsTable.Cols.HEIGHT + " INTEGER, " +
                SettingsTable.Cols.WIDTH + " INTEGER, " +
                SettingsTable.Cols.INIT_CASH + " INTEGER)");

        db.execSQL("CREATE TABLE " + StateTable.NAME + "(" +
                StateTable.Cols.TIME + " INTEGER, " +
                StateTable.Cols.MONEY + " INTEGER, " +
                StateTable.Cols.RES + " INTEGER, " +
                StateTable.Cols.COM + " INTEGER, " +
                StateTable.Cols.INCOME + " INTEGER)");

        db.execSQL("CREATE TABLE " + MapTable.NAME + "(" +
                MapTable.Cols.POS + " INTEGER, " +
                MapTable.Cols.STRUCTURE + " INTEGER, " +
                MapTable.Cols.OWNED + " INTEGER)");

        ContentValues cv = new ContentValues();
        cv.put(SettingsTable.Cols.HEIGHT, 10);
        cv.put(SettingsTable.Cols.WIDTH, 50);
        cv.put(SettingsTable.Cols.INIT_CASH, 1000);
        db.insert(SettingsTable.NAME, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void insertMap(MapElement[][] elems, SQLiteDatabase db)
    {
        for(int ii = 0; ii < elems.length; ii++)
        {
            for (int jj = 0; jj < elems[0].length; jj++)
            {
                ContentValues cv = new ContentValues();
                cv.put(MapTable.Cols.POS, elems[ii][jj].getPos());
                cv.put(MapTable.Cols.STRUCTURE, elems[ii][jj].getStructure().getID());
                cv.put(MapTable.Cols.OWNED, elems[ii][jj].getOwnerName());
                cv.put(MapTable.Cols.GRASS, elems[ii][jj].getGrassType());
                db.insert(MapTable.NAME, null, cv);
            }
        }
    }

    public void updateMapElement(MapElement mapEl, SQLiteDatabase db)
    {
        ContentValues cv = new ContentValues();
        cv.put(MapTable.Cols.STRUCTURE, mapEl.getStructure().getID());
        cv.put(MapTable.Cols.OWNED, mapEl.getOwnerName());
        String[] pos = {String.valueOf(mapEl.getPos())};
        db.update(MapTable.NAME, cv, "position = ?", pos);
    }

    public void setSettings(int height, int width, int cash, SQLiteDatabase db)
    {
        db.delete(SettingsTable.NAME, null, null);
        ContentValues cv = new ContentValues();
        cv.put(SettingsTable.Cols.HEIGHT, height);
        cv.put(SettingsTable.Cols.WIDTH, width);
        cv.put(SettingsTable.Cols.INIT_CASH, cash);
        db.insert(SettingsTable.NAME, null, cv);
    }

    public void setState(int gameTime, int money, int nRes, int nCom, int income, SQLiteDatabase db)
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

    public void reset(SQLiteDatabase db)
    {
        db.delete(StateTable.NAME, null, null);
        db.delete(MapTable.NAME, null, null);
    }

    public class MapCursor extends CursorWrapper
    {
        public MapCursor(Cursor cursor) { super(cursor);}

        public MapElement getElem(int pos)
        {
            return new MapElement(
                    getInt(getColumnIndex("position")),

            )


        }
    }

    public void updateSettings(SQLiteDatabase db, Settings sts)
    {
        Cursor cur = db.query(SettingsTable.NAME,null,null,null,null,null,null);
        sts.setMapWidth(cur.getInt(cur.getColumnIndex(SettingsTable.Cols.WIDTH)));
        sts.setMapHeight(cur.getInt(cur.getColumnIndex(SettingsTable.Cols.HEIGHT)));
        sts.setInitialMoney(cur.getInt(cur.getColumnIndex(SettingsTable.Cols.INIT_CASH)));
        cur.close();
    }

    public void loadGameState(SQLiteDatabase db, GameData gd)
    {
        updateSettings(db, gd.getSettings());
    }

    public void
}
