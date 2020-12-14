package com.example.tema1;

import java.io.Serializable;

public class BookModel implements Serializable {
    private String name;
    private  String author;

    public BookModel(String name, String author) {
        this.name = name;
        this.author = author;
    }
    public BookModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
