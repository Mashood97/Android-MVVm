package com.example.mvvm_practice_app.viewmodel;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_practice_app.model.MovieModel;
import com.example.mvvm_practice_app.model.TodoModel;
import com.example.mvvm_practice_app.network.APIService;
import com.example.mvvm_practice_app.network.RetrofitInstance;
import com.example.mvvm_practice_app.network.TodoService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodoListViewModel extends ViewModel {

    private MutableLiveData<List<TodoModel>> todoList;

    public TodoListViewModel() {
        this.todoList = new MutableLiveData<>();
    }

    public MutableLiveData<List<TodoModel>> getTodoList() {
        return todoList;
    }

    public void makeTodoApiCall(Context context){
        new AsyncTaskTodo(context,todoList).execute();
    }
}

class AsyncTaskTodo extends AsyncTask<Void,Void,Void>{
    private Context context;
    private MutableLiveData<List<TodoModel>> todoList;
    AsyncTaskTodo(Context context,MutableLiveData<List<TodoModel>> list){
        this.context = context;
        this.todoList= list;
    }
    private void makeApiCallOfTodo(){
        TodoService apiService = RetrofitInstance.getRetrofitClient().create(TodoService.class);
        Call<List<TodoModel>> call = apiService.getTodoList();
        call.enqueue(new Callback<List<TodoModel>>() {
            @Override
            public void onResponse(Call<List<TodoModel>> call, Response<List<TodoModel>> response) {
                todoList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<TodoModel>> call, Throwable t) {
                Toast.makeText(context,t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                todoList.postValue(null);
            }
        });
    }

    @Override
    protected Void doInBackground(Void... voids) {
        makeApiCallOfTodo();
        return null;
    }
}
