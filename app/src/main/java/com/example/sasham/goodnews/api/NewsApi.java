package com.example.sasham.goodnews.api;

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
    Call<ArticlesList> getTopHeadlines(
            @Query("country") String countryCode,
            @Query("pageSize") int count,
            @Query("apiKey") String apiKey);@GET("/v2/top-headlines")

    Call<ArticlesList> getTopHeadlinesWithCategory(
            @Query("country") String countryCode,
            @Query("pageSize") int count,
            @Query("apiKey") String apiKey,
            @Query("category") String category);


}
