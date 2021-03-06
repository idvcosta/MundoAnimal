package com.ingrid.mundoanimal.util;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ingrid.mundoanimal.fragments.FragmentStates;
import com.ingrid.mundoanimal.repositories.MundoAnimalRepository;

public abstract class BaseViewModel extends ViewModel {

    protected MutableLiveData<FragmentStates> mutableState;
    protected MundoAnimalRepository repository;

    public BaseViewModel(MundoAnimalRepository repository) {
        this.repository = repository;
        mutableState = new MutableLiveData<>();

        mutableState.setValue(FragmentStates.LOADING_INITIAL_DATA);
    }

    protected abstract void loadData();

    public LiveData<FragmentStates> getState() {
        return mutableState;
    }
}
