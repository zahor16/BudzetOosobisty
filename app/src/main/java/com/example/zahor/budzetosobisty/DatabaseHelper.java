package com.example.zahor.budzetosobisty;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Zahor on 2015-12-02.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Budzet.db";
    public static final String TABLE_WPLATY = "wplaty_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAZWA";
    public static final String COL_3 = "KWOTA";
    public static final String COL_4 = "DATA";
    public static final String TABLE_WYPLATY = "wyplaty_table";
    public static final String COL_T1 = "ID";
    public static final String COL_T2 = "NAZWA";
    public static final String COL_T3 = "KWOTA";
    public static final String COL_T4 = "DATA";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_WPLATY + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAZWA TEXT,KWOTA DOUBLE, DATA TEXT )");
        db.execSQL("create table " + TABLE_WYPLATY + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAZWA TEXT,KWOTA DOUBLE, DATA TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_WPLATY);
        db.execSQL("DROP TABLE IF EXIST " + TABLE_WYPLATY);
        onCreate(db);

    }

    public boolean insertDataWplaty(String nazwa, String kwota, String data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, nazwa);
        contentValues.put(COL_3, kwota);
        contentValues.put(COL_4, data);
        long result = db.insert(TABLE_WPLATY, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }

    public Cursor getAllDataWplaty() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_WPLATY, null);
        return res;
    }

    public boolean insertDataWyplaty(String nazwa, String kwota, String data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_T2, nazwa);
        contentValues.put(COL_T3, kwota);
        contentValues.put(COL_T4, data);
        long result = db.insert(TABLE_WYPLATY, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }

    public Cursor getAllDataWyplaty() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res1 = db.rawQuery("select * from " + TABLE_WYPLATY, null);
        return res1;
    }

    public Cursor sumaWplat() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor sumWplat = db.rawQuery("select sum(KWOTA) from " + TABLE_WPLATY, null);
        return sumWplat;
    }

    public Cursor sumaWyplat() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor sumWyplat = db.rawQuery("select sum(KWOTA) from " + TABLE_WYPLATY, null);
        return sumWyplat;
    }
    public Cursor MaxWplata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor maxWplata = db.rawQuery("select max(KWOTA) from " + TABLE_WPLATY, null);
        return maxWplata;
    }
    public Cursor MaxWyplata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor maxWyplata = db.rawQuery("select max(KWOTA) from " + TABLE_WYPLATY, null);
        return maxWyplata;
    }
    public Cursor MinWplata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor minWplata = db.rawQuery("select min(KWOTA) from " + TABLE_WPLATY, null);
        return minWplata;
    }
    public Cursor MinWyplata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor minWyplata = db.rawQuery("select min(KWOTA) from " + TABLE_WYPLATY, null);
        return minWyplata;
    }
    public Cursor SrWplata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor srWplata = db.rawQuery("select avg(KWOTA) from " + TABLE_WPLATY, null);
        return srWplata;
    }
    public Cursor SrWyplata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor srWyplata = db.rawQuery("select avg(KWOTA) from " + TABLE_WYPLATY, null);
        return srWyplata;
    }
}
