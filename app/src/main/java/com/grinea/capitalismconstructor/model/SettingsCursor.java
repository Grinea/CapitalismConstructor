package com.grinea.capitalismconstructor.model;

import com.grinea.capitalismconstructor.model.DatabaseSchema.SettingsTable.Cols;
import android.database.Cursor;
import android.database.CursorWrapper;

public class SettingsCursor extends CursorWrapper
{
    public SettingsCursor(Cursor cursor)
    {
        super(cursor);
    }

    public int mapWidth()
    {
        return getInt(getColumnIndex(Cols.WIDTH));
    }

    public int mapHeight()
    {
        return getInt(getColumnIndex(Cols.HEIGHT));
    }

    public int initCash()
    {
        return getInt(getColumnIndex(Cols.INIT_CASH));
    }

}
