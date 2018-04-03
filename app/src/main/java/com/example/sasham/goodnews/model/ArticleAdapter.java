package com.example.sasham.goodnews.model;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sasham.goodnews.R;
import com.example.sasham.goodnews.activity.ArticleDetailActivity;
import com.example.sasham.goodnews.utils.DateUtil;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Sasha M on 14.03.2018.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    private Context mContext;
    private List<Article> mArticles;

    public ArticleAdapter(Context context, List<Article> articles) {
        mContext = context;
        this.mArticles = articles;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item, parent, false);
        return new ArticleViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {

        final Article article = mArticles.get(position);

        holder.title.setText(article.getTitle());

        //set published time ago
        String patternTime = mContext.getString(R.string.time_pattern);
        String time;
        try {
            time = DateUtil.convertToTimeAgoString(mContext, article.getPublishedAt(), patternTime);
        } catch (ParseException e) {
            time = article.getPublishedAt();
        }
        holder.time.setText(time);

        holder.source.setText(article.getAuthor());

        //set article image
        if (article.getUrlToImage() != null && !article.getUrlToImage().isEmpty())
            Picasso.get().load(article.getUrlToImage())
                    .placeholder(ContextCompat.getDrawable(
                            mContext,
                            R.drawable.ic_article_image_placeholder))
                    .into(holder.image);
        else {
            holder.image.setImageResource(R.drawable.ic_article_image_placeholder);
        }

        //set item click listener
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, ArticleDetailActivity.class);
                intent.putExtra(ArticleDetailActivity.ARTICELE_ARGS,article);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    public void clear() {
        mArticles.clear();
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {
        private View root;
        private TextView title, time, source;
        private ImageView image;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            root=itemView.findViewById(R.id.article_list_item_container);
            title = (TextView) itemView.findViewById(R.id.article_list_item_title);
            time = (TextView) itemView.findViewById(R.id.article_list_item_published_time);
            source = (TextView) itemView.findViewById(R.id.article_list_item_source_name);
            image = (ImageView) itemView.findViewById(R.id.article_list_item_image);
        }
    }
}
