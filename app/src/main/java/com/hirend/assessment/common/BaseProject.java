package com.hirend.assessment.common;

import android.app.Application;
import androidx.annotation.NonNull;
import android.util.Log;

import com.hirend.assessment.R;


public class BaseProject extends Application {

    private static final String TAG = BaseProject.class.getSimpleName();
    private static BaseProject mAppController;

    public static BaseProject getInstance() {
        return mAppController;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppController = this;

    }

    @NonNull
    public String getBaseUrl() {
        try {
            return getStringFromRes(R.string.base_url);
        } catch (Exception e) {
            Log.e(TAG, "getBaseUrl: " + e.getLocalizedMessage());
        }
        return null;
    }

    public String getStringFromRes(int resId) throws Exception {
        return getString(resId);
    }
}
