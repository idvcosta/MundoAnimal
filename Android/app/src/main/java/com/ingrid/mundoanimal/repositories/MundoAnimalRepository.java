package com.ingrid.mundoanimal.repositories;

import android.content.Context;

import com.ingrid.mundoanimal.model.HomeData;
import com.ingrid.mundoanimal.model.ProductsCategory;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MundoAnimalRepository {

    private final MundoAnimalServer server;

    public MundoAnimalRepository(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://mundo-animal-server.appspot.com/")
                .build();

        server = retrofit.create(MundoAnimalServer.class);
    }

    public void loadHome(Callback<HomeData> callback) {
        server.loadHomeData().enqueue(callback);
    }

    public void loadProductsCategory(Callback<List<ProductsCategory>> callback) {
        server.loadProductsCategory().enqueue(callback);
    }
}
