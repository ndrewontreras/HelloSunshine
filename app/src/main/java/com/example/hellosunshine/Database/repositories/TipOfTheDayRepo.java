package com.example.hellosunshine.Database.repositories;

import android.app.Application;

import com.example.hellosunshine.Database.HelloSunshineDB;
import com.example.hellosunshine.Database.daos.TipOfTheDayDAO;
import com.example.hellosunshine.Database.daos.UserDAO;
import com.example.hellosunshine.Database.entities.TipOfTheDay;
import com.example.hellosunshine.Database.entities.User;

import java.util.List;

public class TipOfTheDayRepo {
    private TipOfTheDayDAO mTODDao;

    private List<TipOfTheDay> mAllTOD;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public TipOfTheDayRepo(Application application) {
        HelloSunshineDB db = HelloSunshineDB.getDatabase(application);
        mTODDao = db.todDao();
        mAllTOD = mTODDao.getAlphabetizedTOD();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.

    public List<TipOfTheDay> getAllTOD() {
        return mAllTOD;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(TipOfTheDay tod) {
        HelloSunshineDB.databaseWriteExecutor.execute(() -> {
            mTODDao.addTOD(tod);
        });
    }

    public TipOfTheDay getTOD(int id) {
        return mTODDao.getTOD(id);
    }



}
