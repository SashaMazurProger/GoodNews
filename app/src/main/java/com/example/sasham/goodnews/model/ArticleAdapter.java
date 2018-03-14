package com.example.sasham.goodnews.model;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sasham.goodnews.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Sasha M on 14.03.2018.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    private List<Article> mArticles;

    public ArticleAdapter(List<Article> articles) {
        this.mArticles = articles;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item, parent, false);
        return new ArticleViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {

        Article article = mArticles.get(position);

        holder.title.setText(article.getTitle());
        holder.time.setText(article.getPublishedAt());
        holder.source.setText(article.getAuthor());

        //set article image
        Picasso.get().load(article.getUrlToImage())
                .placeholder(ContextCompat.getDrawable(
                        holder.image.getContext(),
                        R.drawable.article_list_item_image_placeholder))
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    public void clear() {
        mArticles.clear();
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {

        private TextView title, time, source;
        private ImageView image;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.article_list_item_title);
            time = (TextView) itemView.findViewById(R.id.article_list_item_published_time);
            source = (TextView) itemView.findViewById(R.id.article_list_item_source_name);
            image = (ImageView) itemView.findViewById(R.id.article_list_item_image);
        }
    }
}
