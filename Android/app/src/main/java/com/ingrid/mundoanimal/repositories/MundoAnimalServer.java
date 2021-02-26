package com.ingrid.mundoanimal.repositories;

import com.ingrid.mundoanimal.model.HomeData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MundoAnimalServer {

    @GET("home")
    Call<HomeData> loadHomeData();

}
