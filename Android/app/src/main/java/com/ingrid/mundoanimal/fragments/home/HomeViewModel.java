package com.ingrid.mundoanimal.fragments.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ingrid.mundoanimal.model.HighlightItem;
import com.ingrid.mundoanimal.model.HomeData;
import com.ingrid.mundoanimal.model.Product;
import com.ingrid.mundoanimal.repositories.MundoAnimalRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<FragmentStates> mutableState;
    private MutableLiveData<List<Product>> mutableMostWanted;
    private MutableLiveData<List<HighlightItem>> mutableHighlight;
    private MundoAnimalRepository repository;

    public HomeViewModel(MundoAnimalRepository repository) {
        this.repository = repository;
        mutableState = new MutableLiveData<>();
        mutableMostWanted = new MutableLiveData<>();
        mutableHighlight = new MutableLiveData<>();

        mutableState.setValue(FragmentStates.LOADING_INITIAL_DATA);

        loadData();
    }

    private void loadData() {
        repository.loadHome(new Callback<HomeData>() {
            @Override
            public void onResponse(Call<HomeData> call, Response<HomeData> response) {
                HomeData homeData = response.body();

                mutableState.postValue(FragmentStates.INITIAL_DATA_LOADED);
                mutableMostWanted.postValue(homeData.getMostWanted());
                mutableHighlight.postValue(homeData.getHighlights());
            }

            @Override
            public void onFailure(Call<HomeData> call, Throwable cause) {
                Log.e("HomeViewModel", "erro loading HomeData", cause);
                mutableState.postValue(FragmentStates.LOAD_INITIAL_DATA_ERROR);
            }
        });
    }

    public LiveData<FragmentStates> getState() {
        return mutableState;
    }

    public MutableLiveData<List<Product>> getMostWanted() {
        return mutableMostWanted;
    }

    public MutableLiveData<List<HighlightItem>> getHightlight(){
        return mutableHighlight;
    }
}
