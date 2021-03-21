package com.example.moviemaster.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.moviemaster.ModelClass.Customer;
import com.example.moviemaster.ModelClass.Movie;
import com.example.moviemaster.ModelClass.Product;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    Context context;
    public DbHelper(Context context) {
        super(context, com.example.moviemaster.Database.Constant.DATABASE_NAME, null, com.example.moviemaster.Database.Constant.DATABASE_VERSION);
    this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_MOVIE_TABLE = " CREATE TABLE " + Constant.MOVIE_TABLE_NAME + "("
                + com.example.moviemaster.Database.Constant.ID + " INTEGER PRIMARY KEY,"
                + com.example.moviemaster.Database.Constant.TITLE + " TEXT,"
                + com.example.moviemaster.Database.Constant.YEAR + " TEXT ,"
                + com.example.moviemaster.Database.Constant.DIRECTOR + " TEXT ,"
                + com.example.moviemaster.Database.Constant.ACTORS + " TEXT ,"
                + com.example.moviemaster.Database.Constant.REVIEW + " TEXT ,"
                + com.example.moviemaster.Database.Constant.ISFAVOURIT + " INTEGER ,"
                + com.example.moviemaster.Database.Constant.RATING + " TEXT "
                    +")";

        db.execSQL(CREATE_MOVIE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }
// DATA INSERT=============================================================================

    public boolean addMovie(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Constant.TITLE, movie.getTitle());
        values.put(Constant.YEAR, movie.getYear());
        values.put(Constant.DIRECTOR, movie.getDirector());
        values.put(Constant.ACTORS, movie.getActors());
        values.put(Constant.RATING, movie.getRating());
        values.put(Constant.REVIEW, movie.getReview());
        values.put(Constant.ISFAVOURIT, movie.getIsfavourit());


        if (db.insert(Constant.MOVIE_TABLE_NAME, null, values) > 0) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }

    }

    //END DATA INSERT=============================================================================

    // GET All Movies=============================================================================
    public List<Movie> getMovies() {

        Cursor cursor = null;
        List<Movie> movieList = new ArrayList<>();
        String lsit_query = "SELECT * FROM " + Constant.MOVIE_TABLE_NAME + " ORDER BY "+Constant.TITLE+";";
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery(lsit_query, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex(com.example.moviemaster.Database.Constant.ID));
                    String title = cursor.getString(cursor.getColumnIndex(Constant.TITLE));
                    String YEAR = cursor.getString(cursor.getColumnIndex(Constant.YEAR));
                    String DIRECTOR = cursor.getString(cursor.getColumnIndex(Constant.DIRECTOR));
                    String ACTORS = cursor.getString(cursor.getColumnIndex(Constant.ACTORS));
                    String RATING = cursor.getString(cursor.getColumnIndex(Constant.RATING));
                    String REVIEW = cursor.getString(cursor.getColumnIndex(Constant.REVIEW));
                    Integer ISFAVOURIT = cursor.getInt(cursor.getColumnIndex(Constant.ISFAVOURIT));
                    Movie movie = new Movie(id, title, YEAR, DIRECTOR, ACTORS, RATING, REVIEW, ISFAVOURIT);
                    movieList.add(movie);
                }
                while (cursor.moveToNext());
                cursor.close();
                db.close();
                return movieList;
            }
        }
        else {
            db.close();

        }
        return movieList;
    }

//END======================================

    // GET All Movies=============================================================================
    public Movie getMovieById(String findId) {

        Cursor cursor = null;
        Movie movie=null;
        String lsit_query = "SELECT * FROM " + Constant.MOVIE_TABLE_NAME + " WHERE "+Constant.ID+" = "+findId+";";
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery(lsit_query, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                    int id = cursor.getInt(cursor.getColumnIndex(com.example.moviemaster.Database.Constant.ID));
                    String title = cursor.getString(cursor.getColumnIndex(Constant.TITLE));
                    String YEAR = cursor.getString(cursor.getColumnIndex(Constant.YEAR));
                    String DIRECTOR = cursor.getString(cursor.getColumnIndex(Constant.DIRECTOR));
                    String ACTORS = cursor.getString(cursor.getColumnIndex(Constant.ACTORS));
                    String RATING = cursor.getString(cursor.getColumnIndex(Constant.RATING));
                    String REVIEW = cursor.getString(cursor.getColumnIndex(Constant.REVIEW));
                    Integer ISFAVOURIT = cursor.getInt(cursor.getColumnIndex(Constant.ISFAVOURIT));
                     movie = new Movie(id, title, YEAR, DIRECTOR, ACTORS, RATING, REVIEW, ISFAVOURIT);

                cursor.close();
                db.close();
                return movie;
            }
        }
        else {
            db.close();

        }
        return movie;
    }

//END======================================




    //UPDATE MOVIE BY ID========================
    public boolean updateMovie(Movie movie) {

        SQLiteDatabase db = this.getWritableDatabase();

        try {
            ContentValues cv = new ContentValues();
            cv.put(Constant.TITLE, movie.getTitle());
            cv.put(Constant.YEAR, movie.getYear());
            cv.put(Constant.DIRECTOR, movie.getDirector());
            cv.put(Constant.ACTORS, movie.getActors());
            cv.put(Constant.RATING, movie.getRating());
            cv.put(Constant.REVIEW, movie.getReview());
            cv.put(Constant.ISFAVOURIT, movie.getIsfavourit()); //These Fields should be your String values of actual column names
            db.update(Constant.MOVIE_TABLE_NAME,cv, Constant.ID+"=?",new String[] {String.valueOf(movie.getId())});

            return true;
        }catch (Exception s){
            Toast.makeText(context, "Operation failed: " + s.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }
        finally {
            db.close();
        }



    }
    //=========================================

    //UPDATE FAVOURITE MOVIE LIST==========================

    public boolean updateFavouritMovieList(List<Movie> movieList) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            for (int i = 0; i < movieList.size(); i++) {
                ContentValues cv = new ContentValues();
                cv.put(Constant.ISFAVOURIT, movieList.get(i).getIsfavourit()); //These Fields should be your String values of actual column names
                db.update(Constant.MOVIE_TABLE_NAME, cv, Constant.ID + "=" + movieList.get(i).getId(), null);
            }

            return true;
        }catch (Exception e){
            Toast.makeText(context, "Operation failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
            return false;
        }
        finally {
            db.close();
        }
    }
//=========================================


    //GET FAVOURITE MOVIE LIST=======================
    public List<Movie> get_Fav_movies() {
        Cursor cursor = null;
        List<Movie> movieList = new ArrayList<>();
        String lsit_query = "SELECT * FROM " + Constant.MOVIE_TABLE_NAME + " WHERE "+Constant.ISFAVOURIT+"="+"1"+ ";";
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery(lsit_query, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex(com.example.moviemaster.Database.Constant.ID));
                    String title = cursor.getString(cursor.getColumnIndex(Constant.TITLE));
                    String YEAR = cursor.getString(cursor.getColumnIndex(Constant.YEAR));
                    String DIRECTOR = cursor.getString(cursor.getColumnIndex(Constant.DIRECTOR));
                    String ACTORS = cursor.getString(cursor.getColumnIndex(Constant.ACTORS));
                    String RATING = cursor.getString(cursor.getColumnIndex(Constant.RATING));
                    String REVIEW = cursor.getString(cursor.getColumnIndex(Constant.REVIEW));
                    Integer ISFAVOURIT = cursor.getInt(cursor.getColumnIndex(Constant.ISFAVOURIT));
                    Movie movie = new Movie(id, title, YEAR, DIRECTOR, ACTORS, RATING, REVIEW, ISFAVOURIT);
                    movieList.add(movie);
                }
                while (cursor.moveToNext());
                cursor.close();
                db.close();
                return movieList;
            }
        }
        else {
            db.close();

        }
        return movieList;

    }
//==========================================

    //GET MOVIES BY SEARCH =====================================
    public List<Movie> getMoviesBySearch(String s) {
        Cursor cursor = null;
        List<Movie> movieList = new ArrayList<>();


        String lsit_query = "SELECT * FROM " + Constant.MOVIE_TABLE_NAME + " WHERE "+Constant.TITLE+" LIKE "+"'%"+s+"%'"+" OR "+Constant.REVIEW+" LIKE "+"'%"+s+"%'"+" OR "+Constant.ACTORS+" LIKE "+"'%"+s+"%'"+";";
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery(lsit_query, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex(com.example.moviemaster.Database.Constant.ID));
                    String title = cursor.getString(cursor.getColumnIndex(Constant.TITLE));
                    String YEAR = cursor.getString(cursor.getColumnIndex(Constant.YEAR));
                    String DIRECTOR = cursor.getString(cursor.getColumnIndex(Constant.DIRECTOR));
                    String ACTORS = cursor.getString(cursor.getColumnIndex(Constant.ACTORS));
                    String RATING = cursor.getString(cursor.getColumnIndex(Constant.RATING));
                    String REVIEW = cursor.getString(cursor.getColumnIndex(Constant.REVIEW));
                    Integer ISFAVOURIT = cursor.getInt(cursor.getColumnIndex(Constant.ISFAVOURIT));
                    Movie movie = new Movie(id, title, YEAR, DIRECTOR, ACTORS, RATING, REVIEW, ISFAVOURIT);
                    movieList.add(movie);
                }
                while (cursor.moveToNext());
                cursor.close();
                db.close();
                return movieList;
            }
        }
        else {
            db.close();

        }
        return movieList;

    }
//===========================================

}
