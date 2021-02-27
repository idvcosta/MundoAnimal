package com.ingrid.mundoanimal.repositories;

import android.content.Context;

import com.ingrid.mundoanimal.model.HomeData;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit2.Callback;
import retrofit2.Converter;
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
}
