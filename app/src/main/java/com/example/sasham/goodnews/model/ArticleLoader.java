package com.example.sasham.goodnews.model;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.example.sasham.goodnews.activity.App;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sasha M on 14.03.2018.
 */

public class ArticleLoader extends AsyncTaskLoader<List<Article>> {

    private static final String TAG = ArticleLoader.class.getSimpleName();

    public ArticleLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<Article> loadInBackground() {
        Log.d(TAG, "loadInBackground: ---");

        List<Article> articles;

        Response response = null;

        try {
            response = App.getNewsApi().getTopHeadlines("ua", App.getNewsApiKey()).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArticlesList articlesList = (ArticlesList) response.body();
        articles = articlesList.getArticles();

        return articles;

    }
}
