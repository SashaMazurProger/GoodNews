package com.example.sasham.goodnews.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.sasham.goodnews.R;

public class ArticleWebActivity extends AppCompatActivity {

    private WebView webView;
    public static final String WEB_URL_ARGS = "url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_web);

        webView=(WebView)findViewById(R.id.web_view);
        openWebSite();
    }

    private void openWebSite() {
        String url=getIntent().getStringExtra(WEB_URL_ARGS);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}
