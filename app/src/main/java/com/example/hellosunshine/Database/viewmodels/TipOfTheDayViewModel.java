package com.example.hellosunshine.Database.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.hellosunshine.Database.entities.TipOfTheDay;
import com.example.hellosunshine.Database.entities.User;
import com.example.hellosunshine.Database.repositories.TipOfTheDayRepo;
import com.example.hellosunshine.Database.repositories.UserRepository;

import java.util.List;

public class TipOfTheDayViewModel extends AndroidViewModel {
    private TipOfTheDayRepo mRepository;

    private final List<TipOfTheDay> mAllTOD;

    public TipOfTheDayViewModel(Application application) {
        super(application);
        mRepository = new TipOfTheDayRepo(application);
        mAllTOD = mRepository.getAllTOD();
    }

    public List<TipOfTheDay> getAllTOD() { return mAllTOD; }

    public void insert(TipOfTheDay tod) { mRepository.insert(tod); }

    public TipOfTheDay getTOD(int id) {
        return mRepository.getTOD(id);
    }


}
