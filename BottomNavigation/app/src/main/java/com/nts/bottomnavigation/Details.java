package com.nts.bottomnavigation;

public class Details {

    private int image;
    private String name;
    private String quantity;
    private String price;

    public Details() {
    }

    public Details(int image, String name, String quantity, String price) {
        this.image = image;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price;
    }
}
