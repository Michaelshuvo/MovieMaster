package com.example.moviemaster.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.moviemaster.Adapter.EditPageAdapter;
import com.example.moviemaster.Database.DbHelper;
import com.example.moviemaster.R;

public class EditMovieList extends AppCompatActivity {
RecyclerView recyclerView;
    DbHelper database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie_list);
        getSupportActionBar().setTitle(getResources().getString(R.string.edit_movies_titlebar));
        recyclerView=findViewById(R.id.movielist_editpage_rv);
        DbHelper dbHelper = new DbHelper(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(EditMovieList.this,LinearLayoutManager.VERTICAL,false));
        recyclerView.setHasFixedSize(true);
        EditPageAdapter movieListAdapter = new EditPageAdapter(dbHelper.getMovies(), EditMovieList.this);
        recyclerView.setAdapter(movieListAdapter);

    }
}