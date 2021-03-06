package com.ingrid.mundoanimal.fragments.categorieslist;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ingrid.mundoanimal.fragments.FragmentStates;
import com.ingrid.mundoanimal.model.Category;
import com.ingrid.mundoanimal.repositories.MundoAnimalRepository;
import com.ingrid.mundoanimal.util.BaseViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesListViewModel extends BaseViewModel {

    private MutableLiveData<List<Category>> mutableproductsCategory = new MutableLiveData<>();

    public CategoriesListViewModel(MundoAnimalRepository repository) {
        super(repository);
    }

    @Override
    protected void loadData() {
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

    public LiveData<List<Category>> getCategories() {
        return mutableproductsCategory;
    }
}
