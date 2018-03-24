package com.example.sasham.goodnews.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Sasha M on 23.03.2018.
 */

public class SharedPreferencesHelper {

    private static final String PREF_FILE_NAME = "prefs";

    public static void setSharedPreferenceString(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);

        sharedPreferences.edit()
                .putString(key, value)
                .apply();
    }
    public static String getSharedPreferenceString(Context context, String key, String defValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(key,defValue);
    }

}
