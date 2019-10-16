package com.grinea.capitalismconstructor.model;

//Cursor class for pulling gamestate out of database

import com.grinea.capitalismconstructor.model.DatabaseSchema.StateTable.Cols;

import android.database.Cursor;
import android.database.CursorWrapper;

class StateCursor extends CursorWrapper
{
    StateCursor(Cursor cursor)
    {
        super(cursor);
    }

    int getIncome()
    {
        return getInt(getColumnIndex(Cols.INCOME));
    }

    int getRes()
    {
        return getInt(getColumnIndex(Cols.RES));
    }

    int getCom()
    {
        return getInt(getColumnIndex(Cols.COM));
    }

    int getTime()
    {
        return getInt(getColumnIndex(Cols.TIME));
    }

    int getMoney()
    {
        return getInt(getColumnIndex(Cols.MONEY));
    }
}
