package com.lijing.dev.network.test;

import com.lijing.dev.network.response.ApiResponseObserver;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author lijing
 */
public interface TestApiService {
    @GET("test")
    Observable<ApiResponseObserver<Object>> test();
}
