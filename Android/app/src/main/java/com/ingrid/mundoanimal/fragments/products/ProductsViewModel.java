package com.ingrid.mundoanimal.fragments.products;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ingrid.mundoanimal.fragments.home.FragmentStates;
import com.ingrid.mundoanimal.model.ProductsCategory;
import com.ingrid.mundoanimal.repositories.MundoAnimalRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsViewModel extends ViewModel {

    private MutableLiveData<FragmentStates> mutableState;
    private MutableLiveData<List<ProductsCategory>> mutableproductsCategory;
    private MundoAnimalRepository repository;

    public ProductsViewModel(MundoAnimalRepository repository) {
        this.repository = repository;
        mutableState = new MutableLiveData<>();
        mutableproductsCategory = new MutableLiveData<>();

        mutableState.setValue(FragmentStates.LOADING_INITIAL_DATA);

        loadData();
    }

    private void loadData() {
        repository.loadProductsCategory(new Callback<List<ProductsCategory>>() {
            @Override
            public void onResponse(Call<List<ProductsCategory>> call, Response<List<ProductsCategory>> response) {
                List<ProductsCategory> productsCategories = response.body();

                mutableState.postValue(FragmentStates.INITIAL_DATA_LOADED);
                mutableproductsCategory.postValue(productsCategories);
            }

            @Override
            public void onFailure(Call<List<ProductsCategory>> call, Throwable cause) {
                Log.e("HomeViewModel", "erro loading HomeData", cause);
                mutableState.postValue(FragmentStates.LOAD_INITIAL_DATA_ERROR);
            }
        });
    }

    public LiveData<FragmentStates> getState() {
        return mutableState;
    }

    public LiveData<List<ProductsCategory>> getCategories() {
        return mutableproductsCategory;
    }
}
