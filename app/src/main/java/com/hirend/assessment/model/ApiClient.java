package com.hirend.assessment.model;


import com.hirend.assessment.common.BaseProject;
import com.hirend.assessment.datasource.InfoDataSource;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HirenD on 28/10/19.
 */


public class ApiClient {

    protected String TAG = ApiClient.class.getSimpleName();
    private static Retrofit retrofit = null;

    public static ApiInterface getInterface() {
        return getClient().create(ApiInterface.class);
    }

    private static Retrofit getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BaseProject.getInstance().getBaseUrl())
                    .client(getHttpLoggingInterceptor().build())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

        }
        return retrofit;
    }


    private static OkHttpClient.Builder getHttpLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder();

    }

}
