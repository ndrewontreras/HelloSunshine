package com.example.hellosunshine.Database.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.hellosunshine.Database.repositories.ChildRepository;
import com.example.hellosunshine.Database.entities.Child;

import java.util.List;

public class ChildViewModel extends AndroidViewModel {
    private ChildRepository mRepository;

    private final LiveData<List<Child>> mAllChild;

    public ChildViewModel (Application application) {
        super(application);
        mRepository = new ChildRepository(application);
        mAllChild = mRepository.getAllChildren();
    }

    public LiveData<List<Child>> getAllChildren() { return mAllChild; }

    public void insert(Child child) { mRepository.insert(child); }
}
