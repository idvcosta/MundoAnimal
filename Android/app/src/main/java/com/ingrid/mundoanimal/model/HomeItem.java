package com.ingrid.mundoanimal.model;

public class HomeItem {
    private String title;
    private String price;

    public HomeItem(String title, String price){
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }
}
