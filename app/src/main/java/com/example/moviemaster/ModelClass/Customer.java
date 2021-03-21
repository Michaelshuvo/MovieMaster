package com.example.moviemaster.ModelClass;


public class Customer {
    private int id;
    private String name;
    private String phn;

    public Customer(String name, String phn) {
        this.name = name;
        this.phn = phn;
    }

    public Customer(int id, String name, String phn) {
        this.id = id;
        this.name = name;
        this.phn = phn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhn() {
        return phn;
    }

    public void setPhn(String phn) {
        this.phn = phn;
    }
}
