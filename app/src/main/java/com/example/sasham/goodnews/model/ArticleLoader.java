package com.example.sasham.goodnews.model;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.sasham.goodnews.App;
import com.example.sasham.goodnews.R;
import com.example.sasham.goodnews.utils.NetworkUtil;
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

        return NetworkUtil.getTopHeadlines(getContext(),getContext().getResources().getInteger(R.integer.COUNT_TOP_HEADLINES));
    }
}
