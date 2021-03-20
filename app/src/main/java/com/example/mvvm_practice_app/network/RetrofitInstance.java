package com.example.mvvm_practice_app.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
//https://run.mocky.io/v3/d3d63ea9-4807-47e8-a45f-a6f255a0ed82
    public static String BASE_URL = "https://run.mocky.io/v3/";

    private static Retrofit retrofit;

    public static Retrofit getRetrofitClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return  retrofit;
    }
}
