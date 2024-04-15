package com.example.hellosunshine.Database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hellosunshine.Database.entities.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("select * from users")
    LiveData<List<User>> getAllUsers();

    @Query ("select * from users where user_id==:user_id")
    User getUser(int user_id);

    @Query("SELECT * FROM users ORDER BY users.fullName ASC")
    LiveData<List<User>> getAlphabetizedUsers();

    @Query("SELECT * FROM users WHERE fullName = :fullName")
    User getUserByName(String fullName);








}
