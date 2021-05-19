package com.example.mvvm_practice_app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvvm_practice_app.R;
import com.example.mvvm_practice_app.adapter.MovieListAdapter;
import com.example.mvvm_practice_app.adapter.TodoListAdapter;
import com.example.mvvm_practice_app.model.MovieModel;
import com.example.mvvm_practice_app.model.TodoModel;
import com.example.mvvm_practice_app.viewmodel.MovieListViewModel;
import com.example.mvvm_practice_app.viewmodel.TodoListViewModel;

import java.util.List;

public class TodoActivity extends AppCompatActivity implements TodoListAdapter.ItemClickListener {
    private List<TodoModel> todoList;
    private TodoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        RecyclerView recyclerView = findViewById(R.id.recyclerviews);
        TextView noDataFound = findViewById(R.id.nodatafounds);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layout);
        adapter = new TodoListAdapter(this, todoList, this);
        recyclerView.setAdapter(adapter);

        TodoListViewModel viewmodel = new ViewModelProvider(this).get(TodoListViewModel.class);


        viewmodel.getTodoList().observe(this, new Observer<List<TodoModel>>() {
            @Override
            public void onChanged(List<TodoModel> todoLists) {
                if (todoLists != null) {

                    todoList = todoLists;
                    adapter.setTodoListUpdate(todoLists);
                    noDataFound.setVisibility(View.GONE);
                } else {
                    noDataFound.setVisibility(View.VISIBLE);

                }

            }
        });
        viewmodel.makeTodoApiCall(this);
    }

    @Override
    public void onTodoItemClick(TodoModel model) {
        Toast.makeText(this, model.getTitle(), Toast.LENGTH_SHORT).show();
    }
}