package com.ingrid.mundoanimal.fragments.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ingrid.mundoanimal.model.HomeData;
import com.ingrid.mundoanimal.model.HomeItem;
import com.ingrid.mundoanimal.repositories.MundoAnimalRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<HomeStates> mutableState;
    private MutableLiveData<List<HomeItem>> mutableItems;
    private MundoAnimalRepository repository;

    public HomeViewModel(MundoAnimalRepository repository) {
        this.repository = repository;
        mutableState = new MutableLiveData<>();
        mutableState.setValue(HomeStates.LOADING_INITIAL_DATA);

        mutableItems = new MutableLiveData<>();

        loadData();
    }

    private void loadData() {
        repository.loadHome(new Callback<HomeData>() {
            @Override
            public void onResponse(Call<HomeData> call, Response<HomeData> response) {
                HomeData homeData = response.body();

                mutableState.postValue(HomeStates.INITIAL_DATA_LOADED);
                mutableItems.postValue(homeData.getProducts());
            }

            @Override
            public void onFailure(Call<HomeData> call, Throwable cause) {
                Log.e("HomeViewModel", "erro loading HomeData", cause);
            }
        });
    }

    public LiveData<HomeStates> getState() {
        return mutableState;
    }

    public MutableLiveData<List<HomeItem>> getItems() {
        return mutableItems;
    }
}
