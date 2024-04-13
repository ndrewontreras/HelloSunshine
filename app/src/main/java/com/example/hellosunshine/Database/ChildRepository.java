package com.example.hellosunshine.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.hellosunshine.entities.Child;
import com.example.hellosunshine.entities.User;

import java.util.List;

public class ChildRepository {
    private ChildDAO mChildDao;
    private LiveData<List<Child>> mAllChild;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    ChildRepository(Application application) {
        HelloSunshineDB db = HelloSunshineDB.getDatabase(application);
        mChildDao = db.childDao();
        mAllChild = mChildDao.getAlphabetizedChildren();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Child>> getAllChildren() {
        return mAllChild;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Child child) {
        HelloSunshineDB.databaseWriteExecutor.execute(() -> {
            mChildDao.addChild(child);
        });
    }
}
