package com.ingrid.mundoanimal.model;

import java.util.List;

public class HomeData {
    private List<MostWantedItem> mostWanted;
    private List<HighlightItem> highlights;

    public List<MostWantedItem> getMostWanted() {
        return mostWanted;
    }

    public void setMostWanted(List<MostWantedItem> mostWanted) {
        this.mostWanted = mostWanted;
    }

    public List<HighlightItem> getHighlights() {
        return highlights;
    }
}
