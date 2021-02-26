package com.ingrid.mundoanimal.fragments.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private MutableLiveData<String> mutableText;

    public MainViewModel() {
        mutableText = new MutableLiveData<>();
        mutableText.setValue("Oi");
    }

    public LiveData<String> getText() {
        return mutableText;
    }
}