package com.example.primland.api;

import androidx.core.graphics.TypefaceCompat;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Akitha Iddamalgoda on 8/18/2021.
 **/
public class ApiClient {

    private static Retrofit getRetrofit(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://124.43.13.162:54390")
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public static UserService getUserService(){

        UserService userService = getRetrofit().create(UserService.class);

        return userService;
    }
}
