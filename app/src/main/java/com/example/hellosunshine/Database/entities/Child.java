package com.example.hellosunshine.Database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "child", foreignKeys = {@ForeignKey(entity = User.class, parentColumns = "user_id",
        childColumns = "user_id", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)})

public class Child implements Serializable {

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name="child_id")
    int id = 0;

    @ColumnInfo (name = "f_name")
    String name = " ";

    @ColumnInfo (name = "nickname")
    String nickname = " ";

    @ColumnInfo(name = "age")
    String age =" ";

    @ColumnInfo(name = "gender")
    boolean gender = false;

    @ColumnInfo (name = "user_id")
    int userId = 0;

    public Child() {

    }

    @Ignore
    public Child(String name, String nickname, String age, Boolean gender, int userId) {
        this.name = name;
        this.nickname = nickname;
        this.age = age;
        this.gender = gender;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }
}
