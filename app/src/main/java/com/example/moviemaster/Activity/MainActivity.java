package com.example.moviemaster.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.moviemaster.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //declaring all buttons
       Button registerMovieBtn,favouritesMovieBtn,DisplayMoviesBtn,editMovieBtn,searchBtn,ratingBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initializing all buttton id
        registerMovieBtn=findViewById(R.id.register_movie);
        registerMovieBtn.setOnClickListener(this);
        favouritesMovieBtn=findViewById(R.id.favourit_movie);
        favouritesMovieBtn.setOnClickListener(this);
        DisplayMoviesBtn=findViewById(R.id.display_movie);
        DisplayMoviesBtn.setOnClickListener(this);
        editMovieBtn=findViewById(R.id.edit_movie);
        editMovieBtn.setOnClickListener(this);
        searchBtn=findViewById(R.id.search_movie);
        searchBtn.setOnClickListener(this);

        ratingBtn=findViewById(R.id.rating_movie);
        ratingBtn.setOnClickListener(this);

    }
//all button click listener
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.register_movie){
            startActivity(new Intent(MainActivity.this, Register_Movie.class));
        }
        else if(view.getId()==R.id.favourit_movie){
            startActivity(new Intent(MainActivity.this, Favourites.class));
        }
        else if(view.getId()==R.id.display_movie){
            startActivity(new Intent(MainActivity.this,DisplayMovies.class));
        }
        else if(view.getId()==R.id.edit_movie){
            startActivity(new Intent(MainActivity.this, EditMovieList.class));
        }
        else if(view.getId()==R.id.search_movie){
            startActivity(new Intent(MainActivity.this, SearchMovie.class));
        }
        else if(view.getId()==R.id.rating_movie){
            startActivity(new Intent(MainActivity.this, Ratings.class));
        }
        }

    }
