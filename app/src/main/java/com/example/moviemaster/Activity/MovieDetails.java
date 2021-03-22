package com.example.moviemaster.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.moviemaster.Database.Constant;
import com.example.moviemaster.Database.DbHelper;
import com.example.moviemaster.ModelClass.Movie;
import com.example.moviemaster.R;

public class MovieDetails extends AppCompatActivity {
    //declaring all global variables
    EditText title, year, director, actors, review;
    Movie movie;
    DbHelper dbHelper;
    RatingBar rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        getSupportActionBar().setTitle(getResources().getString(R.string.movie_details_titlebar));
        //initializing all buttton id and edit text id
        dbHelper = new DbHelper(getApplicationContext());
        title = findViewById(R.id.title_edt);
        year = findViewById(R.id.year_edt);
        director = findViewById(R.id.director_edt);
        actors = findViewById(R.id.actors_edt);
        rating = findViewById(R.id.rating_bar);
        review = findViewById(R.id.review_edt);
//get id from previous intent
        int id=getIntent().getIntExtra(Constant.MOVIEID,0);
        //if got id then fetching data by id from database and set data into edittext
        if(id!=0){
            movie=dbHelper.getMovieById(String.valueOf(id));
            if(movie!=null){
                title.setText(movie.getTitle());
                year.setText(movie.getYear());
                director.setText(movie.getDirector());
                actors.setText(movie.getActors());
                rating.setRating(Float.valueOf(movie.getRating()));

                review.setText(movie.getReview());
            }
        }

    }
}