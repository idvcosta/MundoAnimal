package com.ingrid.mundoanimal.fragments.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ingrid.mundoanimal.model.HomeItem;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {


    private MutableLiveData<HomeStates> mutableState;
    private MutableLiveData<List<HomeItem>> mutableItems;

    public HomeViewModel() {
        mutableState = new MutableLiveData<>();
        mutableState.setValue(HomeStates.LOADING_INITIAL_DATA);

        mutableItems = new MutableLiveData<>();

        mock();
    }

    private void mock() {
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mutableState.postValue(HomeStates.INITIAL_DATA_LOADED);

                List<HomeItem> listItems = new ArrayList<>();
                listItems.add(new HomeItem("Coleira", "R$ 15"));
                listItems.add(new HomeItem("Ração", "R$ 35"));
                listItems.add(new HomeItem("Banho", "R$ 40"));
                mutableItems.postValue(listItems);
            }
        }.start();
    }

    public LiveData<HomeStates> getState() {
        return mutableState;
    }

    public MutableLiveData<List<HomeItem>> getItems(){
        return mutableItems;
    }
}
