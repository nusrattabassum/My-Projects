package com.nts.newspapersbd;

public class News {

    private String newsLink;
    private int image;

    public News() {
    }

    public News(int image, String newsLink) {
        this.newsLink = newsLink;
        this.image = image;
    }

    public String getNewsLink() {
        return newsLink;
    }

    public int getImage() {
        return image;
    }
}
