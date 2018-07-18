package com.example.sasham.goodnews.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sasham.goodnews.App;
import com.example.sasham.goodnews.R;
import com.example.sasham.goodnews.model.Article;
import com.example.sasham.goodnews.adapters.ArticleAdapter;
import com.example.sasham.goodnews.adapters.ArticleCategoryAdapter;
import com.example.sasham.goodnews.model.ArticleLoader;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

import static android.view.View.GONE;


public class ListArticlesFragment extends Fragment implements
        LoaderManager.LoaderCallbacks<Object>,
        ArticleCategoryAdapter.CategoryListener {

    private static final String TAG = ListArticlesFragment.class.getSimpleName();
    private RecyclerView mArticlesList;
    private ArticleAdapter mAdapter;
    private AVLoadingIndicatorView mLoadingView;
    private RecyclerView mCategoriesList;
    private TextView mCurrentCategoryView;
    private TextView noConnectionView;
    private RelativeLayout mArticlesContainer;
    private LinearLayout mCategoriesContainer;

    private String mCurrentCategoryTitle;
    private String mCurrentCategoryCode;
    private String[] mCategoryCodes;
    private String[] mCategoryTitles;

    private static final int ARTICLES_LOADER = 1;


    public ListArticlesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_list_articles, container, false);

        noConnectionView = (TextView) rootView.findViewById(R.id.tv_no_internet_connection);
        mCategoriesList = (RecyclerView) rootView.findViewById(R.id.article_categories_recycler_view);
        mArticlesList = (RecyclerView) rootView.findViewById(R.id.top_headlines_recycler_view);
        mLoadingView = (AVLoadingIndicatorView) rootView.findViewById(R.id.articles_list_loading_view);
        mCurrentCategoryView = (TextView) rootView.findViewById(R.id.tv_current_article_category_title);
        mArticlesContainer = (RelativeLayout) rootView.findViewById(R.id.articles_list_container);
        mCategoriesContainer = (LinearLayout) rootView.findViewById(R.id.article_categories_container);

        //Set default article category
        mCurrentCategoryCode = getString(R.string.article_category_all_code);
        mCurrentCategoryTitle = getString(R.string.article_category_default_title);

        initCategoriesList();
        checkConnectionStatus();

        return rootView;
    }

    private void checkConnectionStatus() {
        if (App.isOnline(getContext())) {
            onOnline();
        } else {
            onOffline();
        }
    }

    private void onOffline() {
        mLoadingView.hide();
        noConnectionView.setVisibility(View.VISIBLE);
        mArticlesContainer.setVisibility(View.GONE);
        mCategoriesContainer.setVisibility(View.VISIBLE);
    }

    private void onOnline() {
        mArticlesContainer.setVisibility(View.GONE);
        mCategoriesContainer.setVisibility(View.VISIBLE);
        noConnectionView.setVisibility(GONE);
        mLoadingView.setVisibility(View.VISIBLE);

        mCurrentCategoryView.setText(mCurrentCategoryTitle);

        Bundle args = new Bundle();
        args.putString(ArticleLoader.CATEGORY_ARTICLE_ARGS, mCurrentCategoryCode);
        getLoaderManager().restartLoader(ARTICLES_LOADER, args, this);
    }

    private void initCategoriesList() {
        mCategoryCodes = getContext().getResources().getStringArray(R.array.article_category_codes);
        mCategoryTitles = getContext().getResources().getStringArray(R.array.article_category_titles);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                getContext(),
                LinearLayoutManager.HORIZONTAL,
                false
        );

        mCategoriesList.setLayoutManager(layoutManager);
        mCategoriesList.setAdapter(new ArticleCategoryAdapter(getContext(), mCategoryTitles, this));
    }

    @Override
    public Loader<Object> onCreateLoader(int id, Bundle args) {
        Loader loader = null;

        if (id == ARTICLES_LOADER) {
            loader = new ArticleLoader(getContext(), args);
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Object> loader, Object data) {
        mLoadingView.setVisibility(View.GONE);
        mArticlesContainer.setVisibility(View.VISIBLE);

        List<Article> articles = (List<Article>) data;

        Log.d(TAG, "onLoadFinished: count articles:" + articles.size());

        mAdapter = new ArticleAdapter(getContext(), articles, mCurrentCategoryTitle);
        mArticlesList.setAdapter(mAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mArticlesList.setLayoutManager(layoutManager);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<Object> loader) {
        mAdapter.clear();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void categoryClick(int position) {
        if (mCurrentCategoryCode.equals(mCategoryCodes[position])) {
            return;
        }

        mCurrentCategoryCode = mCategoryCodes[position];
        mCurrentCategoryTitle = mCategoryTitles[position];
        mCurrentCategoryView.setText(mCurrentCategoryTitle);

        onLoaderReset(null);
        checkConnectionStatus();
    }
}
