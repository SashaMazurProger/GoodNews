package com.example.sasham.goodnews.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sasham.goodnews.R;

/**
 * Created by Sasha M on 07.04.2018.
 */

public class ArticleCategoryAdapter extends RecyclerView.Adapter<ArticleCategoryAdapter.CategoryViewHolder> {

    private Context mContext;
    private String[] mCategoryTitles;
    private CategoryListener mCategoryListener;

    public ArticleCategoryAdapter(Context context, String[] categoryTitles,CategoryListener categoryListener) {
        mContext = context;
        mCategoryTitles = categoryTitles;
        mCategoryListener = categoryListener;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(mContext).inflate(R.layout.article_category_list_item, parent, false);
        return new CategoryViewHolder(item);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, final int position) {
        holder.title.setText(mCategoryTitles[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCategoryListener.categoryClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCategoryTitles.length;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private View parentView;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_article_category_title);
            parentView = itemView;
        }
    }

    public interface CategoryListener {
        public void categoryClick(int position);
    }
}
