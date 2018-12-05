package com.dev.angazaproject.data.repository;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * @author kogi
 */
public class RequestService {


    private static final String BASE_URL = "http://api.acme.international/";
    private static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static EndPoints getService(){
       return getRetrofit().create(EndPoints.class);

    }
}
