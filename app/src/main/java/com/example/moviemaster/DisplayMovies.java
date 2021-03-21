package com.example.moviemaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.moviemaster.Adapter.MovieListAdapter;
import com.example.moviemaster.Database.DbHelper;
import com.example.moviemaster.ModelClass.Movie;

import java.util.ArrayList;
import java.util.List;

public class DisplayMovies extends AppCompatActivity {
    //declaring all global variables
    RecyclerView rv;
    Button addFavList;
    DbHelper database;
    List<Movie> currentSelectedMovies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DbHelper dbHelper = new DbHelper(getApplicationContext());
        setContentView(R.layout.activity_display_movies);

        addFavList = findViewById(R.id.add_favourit);


        //set up recycler view
        rv = findViewById(R.id.movie_list_rv);
        rv.setLayoutManager(new LinearLayoutManager(DisplayMovies.this, LinearLayoutManager.VERTICAL, false));
        rv.setHasFixedSize(true);

        //get data from database and set it into recycler adapter to show a movie list
        MovieListAdapter movieListAdapter = new MovieListAdapter(dbHelper.getMovies(), DisplayMovies.this, new MovieListAdapter.OnItemCheckListener() {
            @Override
            public void onItemCheck(Movie movie) {
               // checkbox listener for update favourite list
                currentSelectedMovies.add(movie);

            }


        });

        //set adapter into recyclerview
        rv.setAdapter(movieListAdapter);


        //add favourite list button listener to save favourite movies into database
        addFavList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //before update favourite list check currentselected list if empty
                if (!currentSelectedMovies.isEmpty()) {

                    //update into database of favourite list
                    Boolean isFavUpdated=dbHelper.updateFavouritMovieList(currentSelectedMovies);
                    if(isFavUpdated){
                        Toast.makeText(DisplayMovies.this, "Successfully Update Favourite List", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });
    }
}