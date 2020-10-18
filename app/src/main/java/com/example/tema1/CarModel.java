package com.example.tema1;

public class CarModel {
    private String type;
    private Integer year;

    public CarModel(String type, Integer year) {
        this.type = type;
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public Integer getYear() {
        return year;
    }
}
