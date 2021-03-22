package com.example.moviemaster.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.moviemaster.Adapter.FavMovieListAdapter;
import com.example.moviemaster.Database.DbHelper;
import com.example.moviemaster.ModelClass.Movie;
import com.example.moviemaster.R;

import java.util.ArrayList;
import java.util.List;

public class Favourites extends AppCompatActivity {
    //variables
    RecyclerView rv;
    DbHelper database;
    Button save;
    List<Movie> currentSelectedMovies=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites_movies);
        getSupportActionBar().setTitle(getResources().getString(R.string.favourites_titlebar));
        //declare and initialize database class
        DbHelper dbHelper = new DbHelper(getApplicationContext());


        //get save button id
        save=findViewById(R.id.save_btn);


        //get recyclerview id from xml,set adapter into recycle view and set vertical list
        rv=findViewById(R.id.fav_rv);
        rv.setLayoutManager(new LinearLayoutManager(Favourites.this,LinearLayoutManager.VERTICAL,false));
        rv.setHasFixedSize(true);

        //set data into adapter with checkbox listener for update favourite list
        FavMovieListAdapter movieListAdapter = new FavMovieListAdapter(dbHelper.get_Fav_movies(),getApplicationContext(), new FavMovieListAdapter.OnItemCheckListener() {
            @Override
            public void onItemCheck(Movie movie) {
                currentSelectedMovies.add(movie);
            }
        });
        rv.setAdapter(movieListAdapter);


        //save button listener
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!currentSelectedMovies.isEmpty()) {
                  //favourite list update into database.if update successfully it will return true
                    Boolean isUpdated=dbHelper.updateFavouritMovieList(currentSelectedMovies);

                    if(isUpdated){
                        //if update successfully destroy this activity and go to  mainactivity
                        Toast.makeText(Favourites.this, "Favourite list updated successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                        //if it can't update it will return false and show this toast msg
                        Toast.makeText(Favourites.this, "Something wrong", Toast.LENGTH_SHORT).show();

                    }
                }
                else {
                    Toast.makeText(Favourites.this, "Favourite list updated successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

    }
}