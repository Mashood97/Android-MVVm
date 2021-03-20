package com.example.mvvm_practice_app.network;

import com.example.mvvm_practice_app.model.MovieModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("27db9c07-3e0b-4692-b59a-7bf5ee74e9e6") //continue after base url
    Call<List<MovieModel>> getMovieList();
}
