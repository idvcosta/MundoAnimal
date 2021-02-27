package com.ingrid.mundoanimal.model;

import com.google.gson.annotations.JsonAdapter;
import com.ingrid.mundoanimal.model.adapters.EnumByValueAdapter;

public class HighlightItem {
    private int id;
    private String title;
    @JsonAdapter(EnumByValueAdapter.class)
    private HighlightType type;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public HighlightType getType() {
        return type;
    }
}
