package com.example.sasham.goodnews.utils;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.sasham.goodnews.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Sasha M on 23.03.2018.
 */

public class DateUtil {

    private static final String TAG=DateUtil.class.getSimpleName();

    public static String convertToTimeAgoString(Context context, String timeString, String pattern) throws ParseException {
        String result = null;

        if (timeString == null || pattern == null | timeString.isEmpty() || pattern.isEmpty()) {
            return null;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = dateFormat.parse(timeString);

        long paramMillis = date.getTime();
        long millisNow = new Date().getTime();

        Log.d(TAG, "convertToTimeAgoString: in millis "+paramMillis);

        Date timeAgo = new Date(millisNow - paramMillis);

        long millis = timeAgo.getTime();
        int seconds = (int)Math.abs(millis) / 1000;
        int minutes = seconds / 60;
        int hours = minutes / 60;
        int days = hours / 24;
        int weeks = days / 7;
        int years = days / 365;

        Resources resources = context.getResources();

        String timeSymbol="";
        StringBuilder timeWordsBuilder = new StringBuilder();

        if (years >=1) {
            timeSymbol = resources.getString(R.string.time_ago_years);
            timeWordsBuilder.append(years);
        } else if (weeks >=1) {
            timeSymbol = resources.getString(R.string.time_ago_weeks);
            timeWordsBuilder.append(weeks);
        } else if (days >=1) {
            timeSymbol = resources.getString(R.string.time_ago_days);
            timeWordsBuilder.append(days);
        } else if (hours >=1) {
            timeSymbol = resources.getString(R.string.time_ago_hours);
            timeWordsBuilder.append(hours);
        } else if (minutes >=1) {
            timeSymbol = resources.getString(R.string.time_ago_minutes);
            timeWordsBuilder.append(minutes);
        } else if (seconds > 30) {
            timeSymbol = resources.getString(R.string.time_ago_seconds);
            timeWordsBuilder.append(seconds);
        }

        //for 'now' time ago
        if(seconds<=30){
            String now = resources.getString(R.string.time_ago_now);
            timeWordsBuilder.append(now);
        }
        else{
            String suffix = resources.getString(R.string.time_ago_suffix);
            timeWordsBuilder.append(" ").append(timeSymbol).append(" ").append(suffix);
        }

        result=timeWordsBuilder.toString();
        Log.d(TAG, "convertToTimeAgoString: result - "+result);
        return result;
    }
}
