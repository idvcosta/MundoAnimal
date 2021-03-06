package com.ingrid.mundoanimal.activities.category;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ingrid.mundoanimal.fragments.FragmentStates;
import com.ingrid.mundoanimal.model.LoadCategoryResponse;
import com.ingrid.mundoanimal.model.Product;
import com.ingrid.mundoanimal.repositories.MundoAnimalRepository;
import com.ingrid.mundoanimal.util.BaseViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategoryDetailsViewModel extends BaseViewModel {
    private final MutableLiveData<List<Product>> mutableProducts;
    private int categoryId;

    public CategoryDetailsViewModel(MundoAnimalRepository repository, int categoryId) {
        super(repository);
        this.categoryId = categoryId;
        mutableProducts = new MutableLiveData<>();
    }

    @Override
    protected void loadData() {
        repository.loadCategory(categoryId, new Callback<LoadCategoryResponse>() {

            @Override
            public void onResponse(Call<LoadCategoryResponse> call, Response<LoadCategoryResponse> response) {
                LoadCategoryResponse loadCategoryResponse = response.body();

                mutableState.postValue(FragmentStates.INITIAL_DATA_LOADED);
                mutableProducts.postValue(loadCategoryResponse.getProducts());
            }

            @Override
            public void onFailure(Call<LoadCategoryResponse> call, Throwable cause) {
                Log.e("CategoryViewModel", "erro loading List Products", cause);
                mutableState.postValue(FragmentStates.LOAD_INITIAL_DATA_ERROR);
            }
        });
    }

    public LiveData<FragmentStates> getState() {
        return mutableState;
    }

    public LiveData<List<Product>> getProducts() {
        return mutableProducts;
    }
}
