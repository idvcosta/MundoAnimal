package com.ingrid.mundoanimal.repositories;

import com.ingrid.mundoanimal.model.HomeData;
import com.ingrid.mundoanimal.model.ProductsCategory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MundoAnimalServer {

    @GET("home")
    Call<HomeData> loadHomeData();

    @GET("productsCategories")
    Call<List<ProductsCategory>> loadProductsCategory();
}
