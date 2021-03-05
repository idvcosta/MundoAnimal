package com.ingrid.mundoanimal.fragments.categorieslist;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ingrid.mundoanimal.fragments.FragmentStates;
import com.ingrid.mundoanimal.model.Category;
import com.ingrid.mundoanimal.repositories.MundoAnimalRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesListViewModel extends ViewModel {

    private MutableLiveData<FragmentStates> mutableState;
    private MutableLiveData<List<Category>> mutableproductsCategory;
    private MundoAnimalRepository repository;

    public CategoriesListViewModel(MundoAnimalRepository repository) {
        this.repository = repository;
        mutableState = new MutableLiveData<>();
        mutableproductsCategory = new MutableLiveData<>();

        mutableState.setValue(FragmentStates.LOADING_INITIAL_DATA);

        loadData();
    }

    private void loadData() {
        repository.loadProductsCategory(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                List<Category> productsCategories = response.body();

                mutableState.postValue(FragmentStates.INITIAL_DATA_LOADED);
                mutableproductsCategory.postValue(productsCategories);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable cause) {
                Log.e("ProductsCategoryViewModel", "erro loading HomeData", cause);
                mutableState.postValue(FragmentStates.LOAD_INITIAL_DATA_ERROR);
            }
        });
    }

    public LiveData<FragmentStates> getState() {
        return mutableState;
    }

    public LiveData<List<Category>> getCategories() {
        return mutableproductsCategory;
    }
}
