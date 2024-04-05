package com.example.hellosunshine.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.hellosunshine.entities.Child;
import com.example.hellosunshine.entities.JournalEntries;
import com.example.hellosunshine.entities.ShoppingListItem;
import com.example.hellosunshine.entities.ToDoTasks;
import com.example.hellosunshine.entities.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Child.class, ShoppingListItem.class, ToDoTasks.class, JournalEntries.class},
        version = 1)
public abstract class HelloSunshineDB extends RoomDatabase {
    /*
    private static HelloSunshineDB database;
    private static String DATABASE_NAME = "HelloSunshineApp";

    /*
    public synchronized static HelloSunshineDB getInstance(Context context) {

        if(database == null) {

        }
        return null;
    }

     */

    public abstract UserDAO userDao();

    private static volatile HelloSunshineDB INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static HelloSunshineDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (HelloSunshineDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    HelloSunshineDB.class, "HSDatabase")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /*
    public abstract UserDAO getUserDAO();

     */

}
