package com.example.mvvm_practice_app;


//Setup Library : retrofit2,recycleview,glide,converter-gson,lifecycle-extensions from proj struc-> dependencies

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvvm_practice_app.adapter.MovieListAdapter;
import com.example.mvvm_practice_app.model.MovieModel;
import com.example.mvvm_practice_app.viewmodel.MovieListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieListAdapter.ItemClickListener {

    private List<MovieModel> movieModelList;
    private MovieListAdapter adapter;
    private MovieListViewModel viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        TextView noDataFound = findViewById(R.id.nodatafound);
        LinearLayoutManager layout = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layout);
        adapter = new MovieListAdapter(this, movieModelList, this);
        recyclerView.setAdapter(adapter);

        //initialize view model
        viewmodel = new ViewModelProvider(this).get(MovieListViewModel.class);
        viewmodel.getMoviesMutableList().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if (movieModels != null) {

                    movieModelList = movieModels;
                    adapter.setMovieListUpdate(movieModels);
                    noDataFound.setVisibility(View.GONE);
                } else {
                    noDataFound.setVisibility(View.VISIBLE);

                }

            }
        });
        viewmodel.makeApiCall(this);
    }

    @Override
    public void onMovieClick(MovieModel model) {
        Toast.makeText(this, "Movie Clicked" + model.getTitle(), Toast.LENGTH_SHORT).show();
    }
}