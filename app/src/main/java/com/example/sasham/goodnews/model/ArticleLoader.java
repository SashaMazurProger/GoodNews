package com.example.sasham.goodnews.model;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.sasham.goodnews.R;
import com.example.sasham.goodnews.utils.NetworkUtil;

import java.util.List;

/**
 * Created by Sasha M on 14.03.2018.
 */

public class ArticleLoader extends AsyncTaskLoader<List<Article>> {

    private static final String TAG = ArticleLoader.class.getSimpleName();
    public static final String CATEGORY_ARTICLE_ARGS = "article_category";
    private final String mCategoryCode;


    public ArticleLoader(Context context, Bundle args) {
        super(context);
        mCategoryCode = args.getString(CATEGORY_ARTICLE_ARGS);
        Log.d(TAG, "ArticleLoader: category=" + mCategoryCode);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<Article> loadInBackground() {
        Log.d(TAG, "loadInBackground: ---");

        String categotyAll = getContext().getString(R.string.article_category_all_code);

        List<Article> articles = null;
        if (mCategoryCode.equals(categotyAll)) {
            articles = NetworkUtil.getTopHeadlines(getContext());
        } else {
            articles = NetworkUtil.getTopHeadlinesWithCategory(getContext(), mCategoryCode, null);
        }

        return articles;
    }
}
