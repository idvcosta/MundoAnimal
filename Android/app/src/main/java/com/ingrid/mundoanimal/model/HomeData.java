package com.ingrid.mundoanimal.model;

import java.util.List;

public class HomeData {
    private List<HomeItem> mostWanted;
    private  List<HighlightItem> highlights;

    public List<HomeItem> getMostWanted() {
        return mostWanted;
    }

    public void setMostWanted(List<HomeItem> mostWanted) {
        this.mostWanted = mostWanted;
    }

    public List<HighlightItem> getHighlights() {
        return highlights;
    }
}
