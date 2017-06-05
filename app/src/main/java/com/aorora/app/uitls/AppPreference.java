package com.aorora.app.uitls;

import android.content.Context;
import android.content.SharedPreferences;

import com.aorora.app.base.BasePreferences;

/**
 * Created by Administrator on 2017/5/25.
 */
public class AppPreference extends BasePreferences {

    private static final String FILE_APP_PREFERENCES = "app_preferences";
    private static AppPreference mAppPreference;

    public AppPreference(SharedPreferences sharedPreferences) {
        super(sharedPreferences);
    }

    public static AppPreference getAppPreference() {
        return mAppPreference;
    }

    public static AppPreference init(Context context) {
        if (mAppPreference == null) {
            mAppPreference = new AppPreference(context.getSharedPreferences(FILE_APP_PREFERENCES, Context.MODE_PRIVATE));
        }
        return mAppPreference;
    }

}
