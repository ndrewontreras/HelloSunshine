package com.example.hellosunshine.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.hellosunshine.Database.daos.ChildDAO;
import com.example.hellosunshine.Database.daos.TipOfTheDayDAO;
import com.example.hellosunshine.Database.daos.UserDAO;
import com.example.hellosunshine.Database.entities.Child;
import com.example.hellosunshine.Database.entities.JournalEntries;
import com.example.hellosunshine.Database.entities.ShoppingListItem;
import com.example.hellosunshine.Database.entities.TipOfTheDay;
import com.example.hellosunshine.Database.entities.ToDoTasks;
import com.example.hellosunshine.Database.entities.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Child.class, ShoppingListItem.class, ToDoTasks.class, JournalEntries.class, TipOfTheDay.class},
        version = 5)
public abstract class HelloSunshineDB extends RoomDatabase {

    public abstract UserDAO userDao();

    public abstract ChildDAO childDao();

    public abstract TipOfTheDayDAO todDao();

    private static volatile HelloSunshineDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static HelloSunshineDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (HelloSunshineDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    HelloSunshineDB.class, "HSDatabase").build();
                }
            }
        }
        return INSTANCE;
    }
}
