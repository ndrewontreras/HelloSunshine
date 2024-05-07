package com.example.hellosunshine.Database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hellosunshine.Database.entities.Child;
import com.example.hellosunshine.Database.entities.TipOfTheDay;

import java.util.List;

@Dao
public interface TipOfTheDayDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addTOD(TipOfTheDay tod);

    @Update
    void updateTOD(TipOfTheDay TOD);

    @Delete
    void deleteTOD(TipOfTheDay TOD);

    @Query("select * from tip_of_day")
    LiveData<List<TipOfTheDay>> getAllTOD();

    @Query ("select * from tip_of_day where id ==:todId")
    TipOfTheDay getTOD(int todId);

    @Query("SELECT * FROM tip_of_day ORDER BY title ASC")
    List<TipOfTheDay> getAlphabetizedTOD();
}
