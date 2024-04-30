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

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public UserRepository(Application application) {
        HelloSunshineDB db = HelloSunshineDB.getDatabase(application);
        mUserDao = db.userDao();
        mAllUsers = mUserDao.getAlphabetizedUsers();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public List<User> getAllUsers() {
        return mAllUsers;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(User user) {
        HelloSunshineDB.databaseWriteExecutor.execute(() -> {
            mUserDao.addUser(user);
        });
    }

    public User getUserByEmail(String email) {
        return mUserDao.getUserByName(email);
    }
}
