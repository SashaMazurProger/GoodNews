package com.example.sasham.goodnews.utils;

import android.content.Context;

import com.example.sasham.goodnews.App;
import com.example.sasham.goodnews.R;
import com.example.sasham.goodnews.model.Article;
import com.example.sasham.goodnews.model.ArticlesList;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

/**
 * Created by Sasha M on 07.04.2018.
 */

public class NetworkUtil {

    public static List<Article> getTopHeadlines(Context context) {
        Response response = null;

        String keyCountry = context.getString(R.string.articles_settings_country_key);
        String defCountry = context.getString(R.string.articles_settings_country_code_default);
        String codeCountry = SharedPreferencesHelper.getSharedPreferenceString(context, keyCountry, defCountry);

        try {
            response = App.getNewsApi().getTopHeadlines(codeCountry ,App.getNewsApiKey()).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArticlesList articlesList = null;
        if (response != null) {
            articlesList = (ArticlesList) response.body();
        }
        return articlesList.getArticles();
    }

    public static List<Article> getTopHeadlinesWithCategory(Context context, String categoryCode, String dateOfNewestArticles) {
        Response response = null;

        String keyCountry = context.getString(R.string.articles_settings_country_key);
        String defCountry = context.getString(R.string.articles_settings_country_code_default);
        String codeCountry = SharedPreferencesHelper.getSharedPreferenceString(context, keyCountry, defCountry);

        try {
            response = App.getNewsApi().getTopHeadlinesWithCategory(codeCountry, App.getNewsApiKey(), categoryCode).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArticlesList articlesList = (ArticlesList) response.body();
        return articlesList.getArticles();
    }
    public static List<Article> getSourceArticles(Context context, String source,int pageSize, int page) {
        Response response = null;

        try {
            response = App.getNewsApi().getSourceArticles(source, App.getNewsApiKey(),page,pageSize).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArticlesList articlesList = (ArticlesList) response.body();
        return articlesList.getArticles();
    }
}
