package com.example.sasham.goodnews.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sasham.goodnews.R;

/**
 * Created by Sasha M on 07.04.2018.
 */

public class ArticleCategoryAdapter extends RecyclerView.Adapter<ArticleCategoryAdapter.CategoryViewHolder> {

    private Context mContext;

    public ArticleCategoryAdapter(Context context) {
        mContext = context;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(mContext).inflate(R.layout.article_category_list_item, parent, false);
        return new CategoryViewHolder(item);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        public CategoryViewHolder(View itemView) {
            super(itemView);

        }
    }
}
