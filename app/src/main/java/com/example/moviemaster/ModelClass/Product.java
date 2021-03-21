package com.example.moviemaster.ModelClass;

public class Product {

    private int product_id;
    private String product_name;
    private String product_price;
    private int product_quantity=1;
    public int getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(int product_quantity) {
        this.product_quantity = product_quantity;
    }



    public Product(int product_id, String product_name, String product_price) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_price = product_price;
    }
    public Product(int product_id, String product_name, String product_price, int product_quantity) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_quantity = product_quantity;
    }

    public Product(String product_name, String product_price) {
        this.product_name = product_name;
        this.product_price = product_price;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }
}
