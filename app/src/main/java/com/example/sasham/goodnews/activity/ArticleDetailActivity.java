package com.example.sasham.goodnews.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sasham.goodnews.R;
import com.example.sasham.goodnews.model.Article;
import com.example.sasham.goodnews.utils.DateUtil;
import com.squareup.picasso.Picasso;

import java.text.ParseException;

public class ArticleDetailActivity extends AppCompatActivity {

    private TextView mTitle, mText, mCategory, mSource, mTimeAgo;
    private ImageView mImage;
    private Button mBtnMore;
    private Article mArticle;
    public static final String ARTICELE_ARGS = "article";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        mTitle = (TextView) findViewById(R.id.article_detail_title);
        mText = (TextView) findViewById(R.id.article_detail_text);
        mImage = (ImageView) findViewById(R.id.article_detail_image);
        mCategory = (TextView) findViewById(R.id.article_detail_about_category);
        mSource = (TextView) findViewById(R.id.article_detail_about_source);
        mTimeAgo = (TextView) findViewById(R.id.article_detail_about_time_ago);
        mBtnMore = (Button) findViewById(R.id.btn_article_detail_to_website);

        setArticleData();
    }

    private void setArticleData() {

        mArticle = (Article) getIntent().getSerializableExtra(ARTICELE_ARGS);

        if (mArticle != null) {
            mTitle.setText(mArticle.getTitle());
            mText.setText(mArticle.getDescription());
            mSource.setText(mArticle.getSource().getName());

            //set time ago
            String pattern = getString(R.string.time_pattern);
            String time = "";
            try {
                time = DateUtil.convertToTimeAgoString(this, mArticle.getPublishedAt(), pattern);
            } catch (ParseException e) {
                time = mArticle.getPublishedAt();
            }
            mTimeAgo.setText(time);

            //set category of article
            //TODO: Set article category
            mCategory.setText("Category");

            Picasso.get()
                    .load(mArticle.getUrlToImage())
                    .into(mImage);

            mBtnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(ArticleDetailActivity.this,ArticleWebActivity.class);
                    intent.putExtra(ArticleWebActivity.WEB_URL_ARGS,mArticle.getUrl());
                    startActivity(intent);
                }
            });
        }
    }
}
