package com.example.hellosunshine.Database.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.hellosunshine.Database.HelloSunshineDB;
import com.example.hellosunshine.Database.daos.ChildDAO;
import com.example.hellosunshine.Database.entities.Child;
import com.example.hellosunshine.Database.entities.User;

import java.util.List;

public class ChildRepository {
    private ChildDAO mChildDao;
    private LiveData<List<Child>> mAllChild;

    public ChildRepository(Application application) {
        HelloSunshineDB db = HelloSunshineDB.getDatabase(application);
        mChildDao = db.childDao();
        mAllChild = mChildDao.getAlphabetizedChildren();
    }

    public LiveData<List<Child>> getAllChildren() {
        return mAllChild;
    }


    public void insert(Child child) {
        HelloSunshineDB.databaseWriteExecutor.execute(() -> {
            mChildDao.addChild(child);
        });
    }

    public Child getChildByParent(int userId) {
        return mChildDao.getChildByParent(userId);
    }
}
