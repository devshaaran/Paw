package com.disrupt.paws.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ankit on 10/4/2019.
 */

public class RetroInstance {
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://52773b-19074380.labs.learning.intersystems.com/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
