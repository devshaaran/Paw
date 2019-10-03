package com.disrupt.paws.util;

import com.disrupt.paws.BaseApplication;
import com.disrupt.paws.network.response.PostsResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

public class MiscUtil {

    public static String loadJSONFromAsset(String filename) {
        String json = null;
        try {
            InputStream is = BaseApplication.getInstance().getBaseContext().getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static <T> Object stringToJson(String jsonString, T classType) {
        return new Gson().fromJson(jsonString, PostsResponse.class);
    }

    public static String getJsonNumber(Integer second) {
        switch (second) {
            case 0: return "posts0";
            case 1: return "posts1";
            case 2: return "posts2";
            case 3: return "posts3";
        }
        return "posts0";
    }
}
