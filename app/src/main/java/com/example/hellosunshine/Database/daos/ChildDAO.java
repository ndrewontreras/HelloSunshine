package com.example.hellosunshine.Database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hellosunshine.Database.entities.Child;
import com.example.hellosunshine.Database.entities.User;

import java.util.List;

@Dao
public interface ChildDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addChild(Child child);

    @Update
    void updateChild(Child child);

    @Delete
    void deleteChild(Child child);

    @Query("select * from child")
    LiveData<List<Child>> getAllChildren();

    @Query ("select * from child where id ==:child_id")
    User getUser(int child_id);

    @Query("SELECT * FROM child ORDER BY child.f_name ASC")
    LiveData<List<Child>> getAlphabetizedChildren();

    @Query("SELECT * FROM child WHERE f_Name = :firstName")
    User getUserByName(String firstName);
}
