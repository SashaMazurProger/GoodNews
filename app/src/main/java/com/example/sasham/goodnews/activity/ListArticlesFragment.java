package com.example.sasham.goodnews.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sasham.goodnews.App;
import com.example.sasham.goodnews.R;
import com.example.sasham.goodnews.model.Article;
import com.example.sasham.goodnews.model.ArticleAdapter;
import com.example.sasham.goodnews.model.ArticleLoader;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;


public class ListArticlesFragment extends Fragment implements LoaderManager.LoaderCallbacks<Object> {

    private RecyclerView mRecyclerView;
    private ArticleAdapter mAdapter;

    private static final int ARTICLES_LOADER = 1;
    private AVLoadingIndicatorView mLoadingView;

    public ListArticlesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_list_articles, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.top_headlines_recycler_view);
        mLoadingView=(AVLoadingIndicatorView)rootView.findViewById(R.id.articles_list_loading_view);

        if (App.isOnline(getContext())) {
            getLoaderManager().initLoader(ARTICLES_LOADER, null, this);
            mLoadingView.show();
        } else {
          //TODO: set no connection view
        }
        return rootView;
    }

    @Override
    public Loader<Object> onCreateLoader(int id, Bundle args) {
        Loader loader = null;

        if (id == ARTICLES_LOADER) {
            loader = new ArticleLoader(getContext());
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Object> loader, Object data) {
        mLoadingView.hide();

        List<Article> articles = (List<Article>) data;
        mAdapter = new ArticleAdapter(getContext(),articles);
        mRecyclerView.setAdapter(mAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<Object> loader) {
        mAdapter.clear();
        mAdapter.notifyDataSetChanged();
    }
}
