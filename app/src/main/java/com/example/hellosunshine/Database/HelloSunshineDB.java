package com.example.hellosunshine.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.hellosunshine.entities.Child;
import com.example.hellosunshine.entities.JournalEntries;
import com.example.hellosunshine.entities.ShoppingListItem;
import com.example.hellosunshine.entities.ToDoTasks;
import com.example.hellosunshine.entities.User;

@Database(entities = {User.class, Child.class, ShoppingListItem.class, ToDoTasks.class, JournalEntries.class},
        version = 1)
public abstract class HelloSunshineDB extends RoomDatabase {
    private static HelloSunshineDB database;
    private static String DATABASE_NAME = "HelloSunshineApp";

    /*
    public synchronized static HelloSunshineDB getInstance(Context context) {

        if(database == null) {

        }
        return null;
    }

     */

    public abstract UserDAO getUserDAO();


}
