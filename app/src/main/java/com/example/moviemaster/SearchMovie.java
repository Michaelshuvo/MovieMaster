package com.example.moviemaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.moviemaster.Adapter.SearchResultAdapter;
import com.example.moviemaster.Database.DbHelper;

public class SearchMovie extends AppCompatActivity {
    //declaring all global variables
    RecyclerView recyclerView;
    DbHelper dbHelper;
    Button searchBtn;
    EditText searchbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);
        DbHelper dbHelper = new DbHelper(getApplicationContext());

        //initializing all buttton id and edit text id
        searchBtn = findViewById(R.id.searchBtn);
        searchbox = findViewById(R.id.search_box);

        //search button listener for search specific movie by actors,title or director name
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(searchbox.getText().toString())) {
                    //set searched data into recycler view
                    recyclerView = findViewById(R.id.movielist_by_search_rv);
                    recyclerView.setLayoutManager(new LinearLayoutManager(SearchMovie.this, LinearLayoutManager.VERTICAL, false));
                    recyclerView.setHasFixedSize(true);
                    SearchResultAdapter movieListAdapter = new SearchResultAdapter(dbHelper.getMoviesBySearch(searchbox.getText().toString()), SearchMovie.this);
                    recyclerView.setAdapter(movieListAdapter);
                } else {
                    searchbox.setError("Field not be empty");
                }
            }
        });

    }
}