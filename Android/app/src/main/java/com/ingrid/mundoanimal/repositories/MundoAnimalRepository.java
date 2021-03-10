package com.ingrid.mundoanimal.repositories;

import android.content.Context;

import com.ingrid.mundoanimal.model.LoadCategoryResponse;
import com.ingrid.mundoanimal.model.LoadHomeResponse;
import com.ingrid.mundoanimal.model.Category;
import com.ingrid.mundoanimal.model.LoadServiceResponse;
import com.ingrid.mundoanimal.model.Services;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MundoAnimalRepository {

    private final MundoAnimalServer server;

    public MundoAnimalRepository(Context context) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();



        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://mundo-animal-server.appspot.com/")
                .client(client)
                .build();

        server = retrofit.create(MundoAnimalServer.class);
    }

    public void loadHome(Callback<LoadHomeResponse> callback) {
        server.loadHomeData().enqueue(callback);
    }

    public void loadProductsCategory(Callback<List<Category>> callback) {
        server.loadProductsCategory().enqueue(callback);
    }

    public void loadCategory(int categoryId, Callback<LoadCategoryResponse> callback) {
        server.loadProductsByCategory(categoryId).enqueue(callback);
    }

    public void loadServices(Callback<List<Services>> callback) {
        server.loadServiceData().enqueue(callback);
    }
}
