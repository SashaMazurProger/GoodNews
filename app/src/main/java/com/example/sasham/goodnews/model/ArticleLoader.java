package com.example.sasham.goodnews.model;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.sasham.goodnews.App;
import com.example.sasham.goodnews.R;
import com.example.sasham.goodnews.utils.SharedPreferencesHelper;

import java.io.IOException;
import java.util.List;

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

        String keyCountry=getContext().getString(R.string.articles_settings_country_key);
        String defCountry=getContext().getString(R.string.articles_settings_country_default);
        String codeCountry= SharedPreferencesHelper.getSharedPreferenceString(getContext(),keyCountry,defCountry);

        try {
            response = App.getNewsApi().getTopHeadlines(codeCountry, App.getNewsApiKey()).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArticlesList articlesList = (ArticlesList) response.body();
        articles = articlesList.getArticles();

        return articles;

    }
}
