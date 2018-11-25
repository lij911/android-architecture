package com.lijing.dev.network.test;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author lijing
 */
public interface TestApiService {
    @GET("test")
    Observable<Object> test();
}
