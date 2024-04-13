package com.example.hellosunshine.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "child", foreignKeys = {@ForeignKey(entity = User.class, parentColumns = "user_id",
        childColumns = "user_id", onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)})

public class Child implements Serializable {

    @PrimaryKey (autoGenerate = true)
    int id = 0;

    @ColumnInfo (name = "f_name")
    String name = " ";

    @ColumnInfo (name = "nickname")
    String nickname = " ";

    @ColumnInfo(name = "age")
    String age =" ";

    @ColumnInfo (name = "user_id")
    int userId;

    public Child() {

    }

    public Child(String fName, String nickname, String age) {
        this.name = fName;
        this.nickname = nickname;
        this.age = age;
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

    public void setName(String fName) {
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
}
