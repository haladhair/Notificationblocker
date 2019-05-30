package com.softwareproject.focus.Model;

import android.graphics.drawable.Drawable;

/**
 * Created by Amjad on 02/04/18.
 */

public class app {
    private String name;
    private Drawable icon;
    private String aSwitch ="";

    public app(String name, Drawable icon , String aSwitch) {
        this.name = name;
        this.icon = icon;
        this.aSwitch=aSwitch;
    }

    public app(String name, Drawable icon) {
        this.name = name;
        this.icon = icon;
    }

    public app(String name, String aSwitch) {
        this.name = name;
        this.aSwitch = aSwitch;
    }

    public String getName() {
        return name;
    }

    public Drawable getIcon() {
        return icon;
    }

    public String getaSwitch() {
        return aSwitch;
    }

    public void setaSwitch(String aSwitch) {this.aSwitch = aSwitch;}
}
