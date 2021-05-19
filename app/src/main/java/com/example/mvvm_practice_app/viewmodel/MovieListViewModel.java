package com.example.mvvm_practice_app.viewmodel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_practice_app.model.MovieModel;
import com.example.mvvm_practice_app.network.APIService;
import com.example.mvvm_practice_app.network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListViewModel extends ViewModel {
    private MutableLiveData<List<MovieModel>> moviesList;

    public MovieListViewModel() {
        moviesList = new MutableLiveData<>();
    }

    public MutableLiveData<List<MovieModel>> getMoviesMutableList() {
        return moviesList;
    }

    public void makeApiCall(Context context) {
        APIService apiService = RetrofitInstance.getRetrofitClient().create(APIService.class);
        Call<List<MovieModel>> call = apiService.getMovieList();
        call.enqueue(new Callback<List<MovieModel>>() {
            @Override
            public void onResponse(Call<List<MovieModel>> call, Response<List<MovieModel>> response) {
                moviesList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<MovieModel>> call, Throwable t) {
                Toast.makeText(context,t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                moviesList.postValue(null);
            }
        });
    }
}
