package com.softwareproject.focus.Database;

/**
 * Created by M.Eslim on 07/04/2018.
 */

public class User {
    String Id,Name,Email,Password;


    public User(String name, String email, String password,String id) {
        Id = id;
        Name = name;
        Email = email;
        Password = password;
    }

    public User() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
