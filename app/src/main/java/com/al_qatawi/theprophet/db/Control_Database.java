package com.al_qatawi.theprophet.db;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class Control_Database extends SQLiteAssetHelper {
    private static final String DATA_NAME ="the_prophet.db";
    private static final int version = 11;

    public static final String TABLE_A = "A";
    public static final String TABLE_B = "B";
    public static final String TABLE_C = "C";
    public static final String TABLE_D = "D";
    public static final String TABLE_E = "E";
    public static final String TABLE_F = "F";
    public static final String TABLE_H = "H";
    public static final String TABLE_M = "M";
    public static final String TABLE_S = "S";


    public static final String ID = "ID";
    public static final String TEXT = "TEXT";



    public Control_Database(Context context) {
        super(context, DATA_NAME,  null, version);
    }

}
