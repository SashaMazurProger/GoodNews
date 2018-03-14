package com.example.sasham.goodnews.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Sasha M on 14.03.2018.
 */

public class ArticlesList {
    @SerializedName("articles")
    private List<Article> articles;

    public ArticlesList(List<Article> articles) {
        this.articles = articles;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
