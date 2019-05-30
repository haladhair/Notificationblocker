package com.softwareproject.focus.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.softwareproject.focus.Model.app;

import java.util.Date;
import java.util.List;

/**
 * Created by Amjad on 03/04/18.
 */

public class Profile_database extends SQLiteOpenHelper {

    public static final String name = "profile.db";

    public Profile_database(Context context) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table profile ( name TEXT,Duration_from DATE,Duration_to DATE,repeat_type TEXT,apps_list TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS profile");
        onCreate(db);
    }

    public void Insert_profile(String name, Date from, Date to, String repete_type, List<app> apps_list){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name",name);
        values.put("Duration_from", String.valueOf(from));
        values.put("Duration_to", String.valueOf(to));
        values.put("repeat_type",repete_type);
        values.put("apps_list", String.valueOf(apps_list));
        db.insert("profile",null,values);
    }
}
