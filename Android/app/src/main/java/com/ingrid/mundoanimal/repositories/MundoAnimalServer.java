package com.ingrid.mundoanimal.repositories;

import com.ingrid.mundoanimal.model.LoadCategoryResponse;
import com.ingrid.mundoanimal.model.LoadHomeResponse;
import com.ingrid.mundoanimal.model.Category;
import com.ingrid.mundoanimal.model.LoadServiceResponse;
import com.ingrid.mundoanimal.model.Services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MundoAnimalServer {

    @GET("home")
    Call<LoadHomeResponse> loadHomeData();

    @GET("productsCategories")
    Call<List<Category>> loadProductsCategory();

    @GET("productsByCategory")
    Call<LoadCategoryResponse> loadProductsByCategory(@Query("categoryId") int categoryId);

    @GET("servicos")
    Call<List<Services>> loadServiceData();
}
