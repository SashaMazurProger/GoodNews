package com.example.sasham.goodnews.activity;

import com.example.sasham.goodnews.model.Article;
import com.example.sasham.goodnews.model.ArticlesList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Sasha M on 14.03.2018.
 */

public interface NewsApi {

    @GET("/v2/top-headlines")
    Call<ArticlesList> getTopHeadlines(@Query("country") String countryCode, @Query("apiKey") String apiKey);

}
