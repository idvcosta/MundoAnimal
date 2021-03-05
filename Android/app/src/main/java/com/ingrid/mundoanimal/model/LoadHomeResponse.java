package com.ingrid.mundoanimal.model;

import java.util.List;

public class LoadHomeResponse {
    private List<Product> mostWanted;
    private List<HighlightItem> highlights;

    public List<Product> getMostWanted() {
        return mostWanted;
    }

    public void setMostWanted(List<Product> mostWanted) {
        this.mostWanted = mostWanted;
    }

    public List<HighlightItem> getHighlights() {
        return highlights;
    }
}
