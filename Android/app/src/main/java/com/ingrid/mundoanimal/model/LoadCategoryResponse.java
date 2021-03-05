package com.ingrid.mundoanimal.model;

import java.util.List;

public class LoadCategoryResponse {

    private List<Product> products;
    private String name;

    public List<Product> getProducts() {
        return products;
    }

    public String getName() {
        return name;
    }
}
