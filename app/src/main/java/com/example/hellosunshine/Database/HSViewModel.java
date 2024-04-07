package com.example.hellosunshine.Database;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.hellosunshine.entities.User;

import java.util.List;

public class HSViewModel extends AndroidViewModel {

    private HSRepository mRepository;

    private final LiveData<List<User>> mAllUsers;

    public HSViewModel (Application application) {
        super(application);
        mRepository = new HSRepository(application);
        mAllUsers = mRepository.getAllUsers();
    }

    LiveData<List<User>> getAllUsers() { return mAllUsers; }

    public void insert(User user) { mRepository.insert(user); }
}
