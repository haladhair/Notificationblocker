package com.softwareproject.focus.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.softwareproject.focus.Model.app;
import com.softwareproject.focus.Model.profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amjad on 03/04/18.
 */

public class database extends SQLiteOpenHelper {

    public static final String name = "database.db";
    Context context;
    public database(Context context) {

        super(context, name, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table profile ( name TEXT,Duration_from TEXT,Duration_to TEXT,repeat_type TEXT,apps_list TEXT)");
        db.execSQL("create table app ( app_name TEXT,app_icon BLOB,switch TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS profile");
        db.execSQL("DROP TABLE IF EXISTS app");
        onCreate(db);
    }

    public void Insert_profile(String name, String from, String to, String repeat_type, List<String> apps_list){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name",name);
        values.put("Duration_from", from);
        values.put("Duration_to", to);
        values.put("repeat_type",repeat_type);
        values.put("apps_list", String.valueOf(apps_list));
        db.insert("profile",null,values);
    }

    public void Insert_app(String name, String Switch){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
      //  try {

   //         FileInputStream fs = new FileInputStream(image_path);
      //      byte [] image_byte = new byte[fs.available()];
        //    fs.read(image_byte);

            values.put("app_name",name);
        //  values.put("app_icon", image_byte);
            values.put("switch", Switch);
            db.insert("app",null,values);
        //  fs.close();
        //  Toast.makeText(context.getApplicationContext(),"save",Toast.LENGTH_LONG).show();
        //} catch (FileNotFoundException e) {
        //   e.printStackTrace();
        //  } catch (IOException e) {
        //    e.printStackTrace();
        //    Toast.makeText(context.getApplicationContext(),"not save",Toast.LENGTH_LONG).show();
        //  }
    }

    public void del_app(String name){
        this.getWritableDatabase().delete("app","app_name='"+name+"'",null);
    }

    public void update_app(String name , String s_value){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Values = new ContentValues();
        Values.put("switch",s_value);
        String where = "app_name" + "="+name;
        try {
            db.update("app",Values,where,null);
        }catch (Exception e){
            String error = e.getMessage().toString();
        }
    }

    public ArrayList<profile> getAllProfile(){
        ArrayList<profile> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery("select * from profile", null);
        rs.moveToFirst();
        while (!rs.isAfterLast()) {
            String profile_name = rs.getString(rs.getColumnIndex("name"));
            String Duration_from = rs.getString(rs.getColumnIndex("Duration_from"));
            String Duration_to = rs.getString(rs.getColumnIndex("Duration_to"));
            String repeat_type = rs.getString(rs.getColumnIndex("repeat_type"));
          //  List<String> apps_list = rs.getString(rs.getColumnIndex("apps_list"));
            //  arrayList.add(new profile(profile_name,Duration_from,Duration_to,repeat_type,apps_list));
            rs.moveToNext();
        }
        return arrayList;
    }

    public ArrayList<app> getAllApp(){
        ArrayList<app> arraylist = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery("select * from app", null);
        rs.moveToFirst();
        while (!rs.isAfterLast()) {
            String app_name = rs.getString(rs.getColumnIndex("app_name"));
            String switch_value = rs.getString(rs.getColumnIndex("switch"));
    //        byte [] app_icon = rs.getBlob(rs.getColumnIndex("app_icon"));
       //     Drawable image = new BitmapDrawable(context.getResources(),BitmapFactory.decodeByteArray(app_icon,0,app_icon.length));
       //     arraylist.add(new app(app_name,image,switch_value));
            arraylist.add(new app(app_name,switch_value));
            rs.moveToNext();
        }
        return arraylist;
    }
}
