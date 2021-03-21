package com.example.moviemaster.ModelClass;

public class Movie {
    private int id;
    private String title;
    private String year;
    private String director;
    private String actors;
    private String rating;
    private String review;
    private Integer isfavourit=0;

    public Movie(int id, String title, String year, String director, String actors, String rating, String review, Integer isfavourit) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.director = director;
        this.actors = actors;
        this.rating = rating;
        this.review = review;
        this.isfavourit = isfavourit;
    }

    public Movie(String title, String year, String director, String actors, String rating, String review) {
        this.title = title;
        this.year = year;
        this.director = director;
        this.actors = actors;
        this.rating = rating;
        this.review = review;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getIsfavourit() {
        return isfavourit;
    }

    public void setIsfavourit(Integer isfavourit) {
        this.isfavourit = isfavourit;
    }
}
