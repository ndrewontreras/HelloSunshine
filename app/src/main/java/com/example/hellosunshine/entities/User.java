package com.example.hellosunshine.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "users")
public class User implements Serializable {

    @ColumnInfo(name = "user_id")
    @PrimaryKey (autoGenerate = true)
    int id = 0;

    @ColumnInfo(name = "fullName")
    String fullName =" ";

    @ColumnInfo(name = "email")
    String email =" ";


    @ColumnInfo(name = "pass")
    String pass =" ";


    public User() {

    }

    public User(String email, String pass, String fullName) {
        this.email = email;
        this.pass = pass;
        this.fullName = fullName;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fName) {
        this.fullName = fName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) { this.pass = pass;}
}
