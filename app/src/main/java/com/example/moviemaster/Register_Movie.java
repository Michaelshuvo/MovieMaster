package com.example.moviemaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moviemaster.Database.DbHelper;
import com.example.moviemaster.ModelClass.Movie;

public class Register_Movie extends AppCompatActivity {
    //declaring all global variables
    EditText title, year, director, actors, rating, review;
    Button save;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered__movie);
        //initializing all buttton id and edit text id
        dbHelper = new DbHelper(getApplicationContext());
        title = findViewById(R.id.title_edt);
        year = findViewById(R.id.year_edt);
        director = findViewById(R.id.director_edt);
        actors = findViewById(R.id.actors_edt);
        rating = findViewById(R.id.rating_edt);
        review = findViewById(R.id.review_edt);
        save = findViewById(R.id.save);

        //save button listener for data validation and save data into database
        save.setOnClickListener(new View.OnClickListener() {
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
        String ratingValue = rating.getText().toString();
        String reviewValue = review.getText().toString();
//check all datafield is not blank and user gives valid data
        if (!TextUtils.isEmpty(titleValue)) {
            if (!TextUtils.isEmpty(yearValue) && Integer.valueOf(yearValue) > 1895) {
                if (!TextUtils.isEmpty(directorValue)) {
                    if (!TextUtils.isEmpty(actorsValue)) {
                        if (!TextUtils.isEmpty(ratingValue) && Integer.valueOf(ratingValue) > 0 && Integer.valueOf(ratingValue) <= 10) {
                            if (!TextUtils.isEmpty(reviewValue)) {

                                Boolean isInserted = dbHelper.addMovie(new Movie(titleValue, yearValue, directorValue, actorsValue, ratingValue, reviewValue));
                                if (isInserted) {
                                    Toast.makeText(Register_Movie.this, "Successfully submitted", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Register_Movie.this, "something error", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                review.setError("Please Enter your Review ");
                                review.requestFocus();
                            }
                        } else {
                            rating.setError("Please Give Rating");
                            rating.requestFocus();
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