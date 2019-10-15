package com.grinea.capitalismconstructor.model;


import com.grinea.capitalismconstructor.model.DatabaseSchema.StateTable.Cols;

import android.database.Cursor;
import android.database.CursorWrapper;

public class StateCursor extends CursorWrapper
{
    public StateCursor(Cursor cursor)
    {
        super(cursor);
    }

    public int getIncome()
    {
        return getInt(getColumnIndex(Cols.INCOME));
    }

    public int getRes()
    {
        return getInt(getColumnIndex(Cols.RES));
    }

    public int getCom()
    {
        return getInt(getColumnIndex(Cols.COM));
    }

    public int getTime()
    {
        return getInt(getColumnIndex(Cols.TIME));
    }

    public int getMoney()
    {
        return getInt(getColumnIndex(Cols.MONEY));
    }
}
