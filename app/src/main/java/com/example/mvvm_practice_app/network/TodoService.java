package com.example.mvvm_practice_app.network;

import com.example.mvvm_practice_app.model.MovieModel;
import com.example.mvvm_practice_app.model.TodoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TodoService {
    @GET("1173a0c3-df5d-41a5-b740-87d0a82e5306") //continue after base url
    Call<List<TodoModel>> getTodoList();
}
