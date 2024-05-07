package com.example.hellosunshine.Database.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.hellosunshine.Database.HelloSunshineDB;
import com.example.hellosunshine.Database.daos.UserDAO;
import com.example.hellosunshine.Database.entities.User;

import java.util.List;

public class UserRepository {
    private UserDAO mUserDao;
    private List<User> mAllUsers;

    public UserRepository(Application application) {
        HelloSunshineDB db = HelloSunshineDB.getDatabase(application);
        mUserDao = db.userDao();
        mAllUsers = mUserDao.getAlphabetizedUsers();
    }

    public List<User> getAllUsers() {
        return mAllUsers;
    }

    public void insert(User user) {
        HelloSunshineDB.databaseWriteExecutor.execute(() -> {
            mUserDao.addUser(user);
        });
    }

    public User getUserByEmail(String email) {
        return mUserDao.getUserByName(email);
    }
}
