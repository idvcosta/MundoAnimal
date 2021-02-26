package com.ingrid.mundoanimal.model;

public class HomeItem {
    private String name;
    private String price;

    public HomeItem(String name, String price){
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }
}
