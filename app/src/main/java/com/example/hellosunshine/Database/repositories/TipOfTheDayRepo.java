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

    public TipOfTheDayRepo(Application application) {
        HelloSunshineDB db = HelloSunshineDB.getDatabase(application);
        mTODDao = db.todDao();
        mAllTOD = mTODDao.getAlphabetizedTOD();
    }

    public List<TipOfTheDay> getAllTOD() {
        return mAllTOD;
    }

    public void insert(TipOfTheDay tod) {
        HelloSunshineDB.databaseWriteExecutor.execute(() -> {
            mTODDao.addTOD(tod);
        });
    }

    public TipOfTheDay getTOD(int id) {
        return mTODDao.getTOD(id);
    }



}
