package com.al_qatawi.theprophet.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.al_qatawi.theprophet.modle.Category;
import com.al_qatawi.theprophet.modle.Content;

import java.util.ArrayList;

public class AccesDataBase {

    private static AccesDataBase instant;
    private SQLiteDatabase data;
    private SQLiteOpenHelper openHelper;


    public AccesDataBase(Context context) {

        this.openHelper = new Control_Database(context);

    }

    public static AccesDataBase getInstance(Context context) {

        if (instant == null) {

            instant = new AccesDataBase(context);
        }
        return instant;
    }

    public void open() {

        this.data = openHelper.getWritableDatabase();

    }

    public void close() {

        if (this.data != null) {

            this.data.close();
        }
    }


    public ArrayList<Content> getData(String tableName) {

        ArrayList<Content> contents = new ArrayList<>();
        Cursor cursor = data.rawQuery("SELECT * FROM " + tableName, null);

        if (cursor != null && cursor.moveToFirst()) {

            do {

                int id = cursor.getInt(cursor.getColumnIndex(Control_Database.ID + ""));
                String name = cursor.getString(cursor.getColumnIndex(Control_Database.TEXT));

                Content content = new Content(id, name);
                contents.add(content);

            }
            while (cursor.moveToNext());
            cursor.close();

        }
        return contents;

    }



}
