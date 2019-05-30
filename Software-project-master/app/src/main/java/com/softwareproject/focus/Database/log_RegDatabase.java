package com.softwareproject.focus.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by M.Eslim on 07/04/2018.
 */

public class log_RegDatabase extends SQLiteOpenHelper {
    public static final String DB_name = "data.db";

    public log_RegDatabase(Context context) {
        super(context, DB_name, null, 2);
    }

    @Override

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table mytable (id INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT ,Email TEXT,Pass TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS mytable");
        onCreate(db);

    }

    public boolean insertData(String Name,String Email, String Pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", Name);
        contentValues.put("email", Email);
        contentValues.put("pass ", Pass);
        Long result = db.insert("mytable", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public ArrayList getallRecord() {
        ArrayList arraylist = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from mytable ", null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            String t1 = res.getString(res.getColumnIndex("id"));
            String t2 = res.getString(res.getColumnIndex("Name"));
            String t3 = res.getString(res.getColumnIndex("Email"));
            String t4 = res.getString(res.getColumnIndex("Pass"));


             arraylist.add(t1 + ":" + t2 + ":" + t3 + " : "+ t4);
         //  User myuser = new User(t1,t2,t3,t4);

          //  arraylist.add(myuser);
            res.moveToNext();
        }
        return arraylist;
    }

}
