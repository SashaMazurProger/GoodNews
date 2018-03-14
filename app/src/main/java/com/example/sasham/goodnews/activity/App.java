package com.example.sasham.goodnews.activity;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sasha M on 14.03.2018.
 */

public class App extends Application {

    private static NewsApi newsApi;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit=new Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        newsApi=retrofit.create(NewsApi.class);
    }
    public static NewsApi getNewsApi()
    {
        return newsApi;
    }
}
