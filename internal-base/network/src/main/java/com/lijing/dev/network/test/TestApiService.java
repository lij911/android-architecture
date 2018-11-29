package com.lijing.dev.network.test;

import com.lijing.dev.network.response.ApiResponseModel;
import com.lijing.dev.network.response.ApiResponseObserver;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author lijing
 */
public interface TestApiService {
    @GET("test")
    Observable<ApiResponseModel<String>> test();
}
