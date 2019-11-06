package com.hirend.assessment.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.hirend.assessment.presenter.MainPresenter;

/**
 * Created by HirenD on 28/10/19.
 */

public class CodeSnippets {

    protected String TAG = CodeSnippets.class.getSimpleName();
    private Context mContext;

    public CodeSnippets(Context mContext) {
        this.mContext = mContext;
    }

    public boolean hasNetworkConnection() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
       /* if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            } else return activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE;
        }
        return false;*/
        return activeNetwork == null;
    }


}
