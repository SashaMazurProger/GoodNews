package com.example.sasham.goodnews.model;

/**
 * Created by Sasha M on 06.04.2018.
 */

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.sasham.goodnews.R;
import com.example.sasham.goodnews.activity.ArticleDetailActivity;
import com.example.sasham.goodnews.utils.NetworkUtil;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class NewsFirebaseJobService extends JobService {
    private static final String NOTIF_CHANNEL_ID = "channel_id";
    private static final int NOTIFY_ID = 1;
    private static final String TAG = NewsFirebaseJobService.class.getSimpleName();
    private static final int PENDING_INTENT_REQUEST_CODE = 2;

    @Override
    public boolean onStartJob(JobParameters job) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Article article = NetworkUtil.getTopHeadlines(getApplicationContext()).get(0);

                NotificationCompat.Builder notifBuilder = null;
                try {
                    Intent intent = new Intent(getApplicationContext(), ArticleDetailActivity.class);
                    intent.putExtra(ArticleDetailActivity.ARTICLE_ARGS, article);

                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                            PENDING_INTENT_REQUEST_CODE,
                            intent,
                            PendingIntent.FLAG_UPDATE_CURRENT);

                    notifBuilder = new NotificationCompat.Builder(getApplicationContext(), NOTIF_CHANNEL_ID)
                            .setContentTitle(getApplicationContext().getString(R.string.app_name))
                            .setContentText(article.getTitle())
                            .setSmallIcon(R.drawable.ccp_down_arrow)
                            .setLargeIcon(Picasso.get()
                                    .load(article.getUrlToImage())
                                    .placeholder(R.drawable.ic_article_image_placeholder)
                                    .get()
                            )
                            .setContentIntent(pendingIntent);
                    Log.d(TAG, "doInBackground: builded");
                } catch (IOException e) {
                    Log.e(TAG, "doInBackground: ", e);
                }

                if (notifBuilder != null) {
                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.notify(NOTIFY_ID, notifBuilder.build());
                    Log.d(TAG, "doInBackground: notified");
                }
                return null;
            }
        }.execute();

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false;
    }
}
