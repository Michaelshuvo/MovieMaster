package com.example.moviemaster.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.moviemaster.Database.Constant;
import com.example.moviemaster.Database.DbHelper;
import com.example.moviemaster.ModelClass.Movie;
import com.example.moviemaster.R;

public class EditMovie extends AppCompatActivity {
    EditText title, year, director, actors, review;
    Button update;
    Movie movie;
    DbHelper dbHelper;
    RatingBar rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);
        getSupportActionBar().setTitle(getResources().getString(R.string.edit_movie_titlebar));
        dbHelper = new DbHelper(getApplicationContext());
        title = findViewById(R.id.title_edt);
        year = findViewById(R.id.year_edt);
        director = findViewById(R.id.director_edt);
        actors = findViewById(R.id.actors_edt);
        rating = findViewById(R.id.rating_bar);
        review = findViewById(R.id.review_edt);
        update = findViewById(R.id.updateBtn);

        int id=getIntent().getIntExtra(Constant.MOVIEID,0);
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
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              datavalidation();
            }
        });


    }
    private void datavalidation() {
        //get all data from input field
        String titleValue = title.getText().toString();
        String yearValue = year.getText().toString();
        String directorValue = director.getText().toString();
        String actorsValue = actors.getText().toString();
        String ratingValue = String.valueOf(rating.getRating());
        String reviewValue = review.getText().toString();
//check all datafield is not blank and user gives valid data
        if (!TextUtils.isEmpty(titleValue)) {
            if (!TextUtils.isEmpty(yearValue) && Integer.valueOf(yearValue) > 1895) {
                if (!TextUtils.isEmpty(directorValue)) {
                    if (!TextUtils.isEmpty(actorsValue)) {
                        if (!TextUtils.isEmpty(ratingValue) && Float.valueOf(ratingValue) > 0 && Float.valueOf(ratingValue) <= 10) {
                            if (!TextUtils.isEmpty(reviewValue)) {

                                Boolean isUpdated = dbHelper.updateMovie(new Movie(movie.getId(),titleValue, yearValue, directorValue, actorsValue, ratingValue, reviewValue,movie.getIsfavourit()));
                                if (isUpdated) {
                                    Toast.makeText(EditMovie.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    Toast.makeText(EditMovie.this, "something error", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                review.setError("Please Enter your Review ");
                                review.requestFocus();
                            }
                        } else {
                            Toast.makeText(EditMovie.this, "Please give rating", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        actors.setError("Please Enter Name of Actor/Actress of Movie");
                        actors.requestFocus();
                    }
                } else {
                    director.setError("Please Enter Director Name of Movie");
                    director.requestFocus();
                }
            } else {
                year.setError("Please Enter Release Year of Movie");
                year.requestFocus();
            }
        } else {
            title.setError("Please Enter A Movie Title");
            title.requestFocus();
        }
    }
}