package com.nts.bottomnavigation;

public class Product {

    private String name;
    int image;

    public Product() {
    }

    public Product(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }
}
