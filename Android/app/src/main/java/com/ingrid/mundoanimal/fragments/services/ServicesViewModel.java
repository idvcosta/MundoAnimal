package com.ingrid.mundoanimal.fragments.services;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.ingrid.mundoanimal.fragments.FragmentStates;
import com.ingrid.mundoanimal.model.LoadServiceResponse;
import com.ingrid.mundoanimal.model.Services;
import com.ingrid.mundoanimal.repositories.MundoAnimalRepository;
import com.ingrid.mundoanimal.util.BaseViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicesViewModel extends BaseViewModel {
    private MutableLiveData<List<Services>> mutableServices = new MutableLiveData<>();

    public ServicesViewModel(MundoAnimalRepository repository) {
        super(repository);
    }

    @Override
    protected void loadData() {
        repository.loadServices(new Callback<List<Services>>() {
            @Override
            public void onResponse(Call<List<Services>> call, Response<List<Services>> response) {
                List<Services> serviceResponse = response.body();

                mutableState.postValue(FragmentStates.INITIAL_DATA_LOADED);
                mutableServices.postValue(serviceResponse);
            }

            @Override
            public void onFailure(Call<List<Services>> call, Throwable cause) {
                Log.e("ServiceViewModel", "erro loading ServiceData", cause);
                mutableState.postValue(FragmentStates.LOAD_INITIAL_DATA_ERROR);
            }
        });
    }

    public MutableLiveData<List<Services>> getServices() {
        return mutableServices;
    }
}
