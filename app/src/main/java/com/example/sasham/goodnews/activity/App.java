package com.example.sasham.goodnews.activity;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sasha M on 14.03.2018.
 */

public class App extends Application {

    private static NewsApi newsApi;
    private Retrofit retrofit;

    private static final String NEWS_API_KEY = "7759cb618d104c92a73a00d332681d76";

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        newsApi = retrofit.create(NewsApi.class);
    }

    public static NewsApi getNewsApi() {
        return newsApi;
    }

    public static String getNewsApiKey() {
        return NEWS_API_KEY;
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }
}
