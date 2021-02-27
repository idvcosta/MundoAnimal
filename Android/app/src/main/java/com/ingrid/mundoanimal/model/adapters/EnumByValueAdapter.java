package com.ingrid.mundoanimal.model.adapters;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.ingrid.mundoanimal.model.HighlightType;

import java.lang.reflect.Type;

public class EnumByValueAdapter implements JsonDeserializer<HighlightType> {

    @Override
    public HighlightType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        int highlightInt = json.getAsInt();
        HighlightType result = null;

        switch (highlightInt) {
            case 1:
                result = HighlightType.News;
                break;
            case 2:
                result = HighlightType.Sale;
                break;
            case 3:
                result = HighlightType.Event;
                break;

        }

        return result;
    }
}
