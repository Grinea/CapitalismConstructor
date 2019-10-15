package com.grinea.capitalismconstructor.model;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.grinea.capitalismconstructor.model.DatabaseSchema.MapTable.Cols;

public class MapCursor extends CursorWrapper
{
    public MapCursor(Cursor cursor)
    {
        super(cursor);
    }

    public MapElement getElement()
    {
        int pos = getInt(getColumnIndex(Cols.POS));
        int structure = getInt(getColumnIndex(Cols.STRUCTURE));
        String owner = getString(getColumnIndex(Cols.OWNED));
        int grass = getInt(getColumnIndex(Cols.GRASS));

        return new MapElement(pos, structure, owner, grass);
    }
}
