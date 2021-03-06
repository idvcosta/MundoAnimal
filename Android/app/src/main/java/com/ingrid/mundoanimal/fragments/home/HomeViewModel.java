package com.ingrid.mundoanimal.fragments.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ingrid.mundoanimal.fragments.FragmentStates;
import com.ingrid.mundoanimal.model.HighlightItem;
import com.ingrid.mundoanimal.model.LoadHomeResponse;
import com.ingrid.mundoanimal.model.Product;
import com.ingrid.mundoanimal.repositories.MundoAnimalRepository;
import com.ingrid.mundoanimal.util.BaseViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends BaseViewModel {

    private MutableLiveData<List<Product>> mutableMostWanted = new MutableLiveData<>();
    private MutableLiveData<List<HighlightItem>> mutableHighlight = new MutableLiveData<>();

    public HomeViewModel(MundoAnimalRepository repository) {
        super(repository);
    }

    @Override
    protected void loadData() {
        repository.loadHome(new Callback<LoadHomeResponse>() {
            @Override
            public void onResponse(Call<LoadHomeResponse> call, Response<LoadHomeResponse> response) {
                LoadHomeResponse loadHomeResponse = response.body();

                mutableState.postValue(FragmentStates.INITIAL_DATA_LOADED);
                mutableMostWanted.postValue(loadHomeResponse.getMostWanted());
                mutableHighlight.postValue(loadHomeResponse.getHighlights());
            }

            @Override
            public void onFailure(Call<LoadHomeResponse> call, Throwable cause) {
                Log.e("HomeViewModel", "erro loading HomeData", cause);
                mutableState.postValue(FragmentStates.LOAD_INITIAL_DATA_ERROR);
            }
        });
    }


    public MutableLiveData<List<Product>> getMostWanted() {
        return mutableMostWanted;
    }

    public MutableLiveData<List<HighlightItem>> getHightlight(){
        return mutableHighlight;
    }
}
