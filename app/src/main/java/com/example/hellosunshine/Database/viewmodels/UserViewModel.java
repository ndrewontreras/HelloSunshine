package com.example.hellosunshine.Database.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.hellosunshine.Database.repositories.UserRepository;
import com.example.hellosunshine.Database.entities.User;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository mRepository;

    private final List<User> mAllUsers;

    public UserViewModel(Application application) {
        super(application);
        mRepository = new UserRepository(application);
        mAllUsers = mRepository.getAllUsers();
    }

    public List<User> getAllUsers() { return mAllUsers; }

    public void insert(User user) { mRepository.insert(user); }

    public LiveData<User> getUser() {
        return null;
    }

    public User getUserByEmail(String email) {
        return mRepository.getUserByEmail(email);
    }
}
