package com.example.sasham.goodnews.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.sasham.goodnews.R;
import com.example.sasham.goodnews.model.NewsFirebaseJobService;
import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

public class NewsActivity extends AppCompatActivity {

    private static final String SHOW_LAST_ARTICLE_JOB_SERVICE_TAG = "last_article_service";
    private static final String TAG = NewsActivity.class.getSimpleName();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragmentManager.beginTransaction()
                            .replace(R.id.main_content_container, new ListArticlesFragment())
                            .commit();
                    return true;
                case R.id.articles_settings:
                    fragmentManager.beginTransaction()
                            .replace(R.id.main_content_container, new ArticlesSettingsFragment())
                            .commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        setDefaultContent();
        initFirebaseJobDispatcher();
    }

    private void setDefaultContent() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.main_content_container, new ListArticlesFragment())
                .commit();
    }

    private void initFirebaseJobDispatcher() {
        FirebaseJobDispatcher firebaseJobDispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));

        Job job = firebaseJobDispatcher.newJobBuilder()
                .setService(NewsFirebaseJobService.class)
                .setTag(SHOW_LAST_ARTICLE_JOB_SERVICE_TAG)
                .setLifetime(Lifetime.UNTIL_NEXT_BOOT)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(0, 5))
                .setReplaceCurrent(false)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .build();

        firebaseJobDispatcher.mustSchedule(job);
        Log.d(TAG, "initFirebaseJobDispatcher: ok");
    }

}
