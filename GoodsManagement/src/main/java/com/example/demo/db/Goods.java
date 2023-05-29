package com.example.demo.db;

public class Goods {
    private int id;
    private String name;
    private int stock;
    private String categoly;
    private int price;

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoly() {
        return categoly;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategoly(String categoly) {
        this.categoly = categoly;
    }
}
