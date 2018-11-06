package com.helio4.bancol.avaluos.servicio.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonHelper {

    private static GsonBuilder gsonBuilder;

    public static GsonBuilder getGsonBuilder() {
        if (gsonBuilder != null) {
            return gsonBuilder;
        }
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting().serializeNulls();
        return gsonBuilder;
    }

    public static Gson getGson() {
        return getGsonBuilder().create();
    }

}
