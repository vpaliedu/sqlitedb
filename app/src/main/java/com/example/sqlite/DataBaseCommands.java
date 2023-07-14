package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class DataBaseCommands {
    DataBaseHelper dataBaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;

    protected List<ListItem> getData(Context context){
        dataBaseHelper = new DataBaseHelper(context);
        db = dataBaseHelper.getWritableDatabase();
        List<ListItem>listItems = new ArrayList<> ();

        userCursor = db.query(DataBaseHelper.TABLE, null, null, null, null, null, null);
        if (userCursor.moveToFirst()) {
            do {
                int id = userCursor.getInt(userCursor.getColumnIndex(DataBaseHelper.COLUMN_ID));
                String name = userCursor.getString(userCursor.getColumnIndex(DataBaseHelper.COLUMN_NAME));
                String email = userCursor.getString(userCursor.getColumnIndex(DataBaseHelper.COLUMN_EMAIL));

                ListItem listItem = new ListItem(id+"",name,email);
                listItems.add(listItem);
                // Itt feldolgozhatod az adatokat, pl. kiírhatod őket a logba:
                Log.d("Database", "ID: " + id + ", Name: " + name + ", Email: " + email);
            } while (userCursor.moveToNext());
        }

        userCursor.close();
        db.close();
        return listItems;
    }

    protected void saveData(String name, String email, Context context){
        dataBaseHelper = new DataBaseHelper(context);
        db = dataBaseHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(DataBaseHelper.COLUMN_NAME, name);
        cv.put(DataBaseHelper.COLUMN_EMAIL, email);

        db.insert(DataBaseHelper.TABLE, null, cv);

        Cursor cursor = db.query(DataBaseHelper.TABLE, null, null, null, null, null, null);
        cursor.close();
        db.close();
    }

    protected void deleteData(String id, Context context){
        dataBaseHelper = new DataBaseHelper(context);
        db = dataBaseHelper.getWritableDatabase();

        db.delete(DataBaseHelper.TABLE, DataBaseHelper.COLUMN_ID + " = ?", new String[] { id });

        Cursor cursor = db.query(DataBaseHelper.TABLE, null, null, null, null, null, null);
        cursor.close();
        db.close();
    }

}
