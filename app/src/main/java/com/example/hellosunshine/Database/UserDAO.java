package com.example.hellosunshine.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hellosunshine.entities.User;

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

    @Query("SELECT * FROM users ORDER BY users.username ASC")
    LiveData<List<User>> getAlphabetizedUsers();




}
