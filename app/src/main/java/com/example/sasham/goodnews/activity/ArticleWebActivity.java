package com.example.sasham.goodnews.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.sasham.goodnews.R;

public class ArticleWebActivity extends AppCompatActivity {

    private WebView webView;
    public static final String WEB_URL_ARGS = "url";
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_web);

        mToolbar=(Toolbar)findViewById(R.id.base_toolbar);
        webView=(WebView)findViewById(R.id.web_view);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        openWebSite();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void openWebSite() {
        String url=getIntent().getStringExtra(WEB_URL_ARGS);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
        setTitle(webView.getTitle());
    }
}
