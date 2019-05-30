package com.softwareproject.focus.Model;

import java.util.List;

/**
 * Created by Amjad on 03/04/18.
 */

public class profile {

    private String name;
    private String Duration_from;
    private String Duration_to;
    private String repeat_type;
    private List<app> list_app;

    public profile(String name, String duration_from, String duration_to, String repeat_type) {
        this.name = name;
        Duration_from = duration_from;
        Duration_to = duration_to;
        this.repeat_type = repeat_type;
    }

    public profile(String name, String duration_from, String duration_to, String repeat_type, List<app> list_app) {
        this.name = name;
        Duration_from = duration_from;
        Duration_to = duration_to;
        this.repeat_type = repeat_type;
        this.list_app = list_app;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration_from() {
        return Duration_from;
    }

    public void setDuration_from(String duration_from) {
        Duration_from = duration_from;
    }

    public String getDuration_to() {
        return Duration_to;
    }

    public void setDuration_to(String duration_to) {
        Duration_to = duration_to;
    }

    public String getRepeat_type() {
        return repeat_type;
    }

    public void setRepeat_type(String repeat_type) {
        this.repeat_type = repeat_type;
    }

    public List<app> getList_app() {
        return list_app;
    }

    public void setList_app(List<app> list_app) {
        this.list_app = list_app;
    }
}
