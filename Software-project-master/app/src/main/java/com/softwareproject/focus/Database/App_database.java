package com.softwareproject.focus.Database;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.softwareproject.focus.Model.app;

import java.util.ArrayList;

/**
 * Created by Amjad on 03/04/18.
 */

public class App_database extends SQLiteOpenHelper {

    public static final String name = "app.db";

    public App_database(Context context) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table app ( app_name TEXT, switch TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS app");
        onCreate(db);
    }
    public void Insert_app(String name, String Switch){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("app_name",name);
        values.put("switch", Switch);
        db.insert("app",null,values);
    }

    public ArrayList<app> getAllApp(){
        ArrayList<app> arraylist = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery("select * from app", null);
        rs.moveToFirst();
        while (!rs.isAfterLast()) {
            String app_name = rs.getString(rs.getColumnIndex("app_name"));
            String switch_value = rs.getString(rs.getColumnIndex("switch"));
            arraylist.add(new app(app_name,switch_value));
            rs.moveToNext();
        }
        return arraylist;
    }
}
