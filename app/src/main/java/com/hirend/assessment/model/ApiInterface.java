package com.hirend.assessment.model;


import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by HirenD on 28/10/19.
 */

public interface ApiInterface {

    @GET("facts.json")
    Observable<Response<ResponseBody>> getInfo();

}
