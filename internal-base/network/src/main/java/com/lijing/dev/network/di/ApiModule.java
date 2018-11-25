package com.lijing.dev.network.di;


import com.lijing.dev.network.test.TestApiService;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Api Service 相关
 *
 * @author lijing
 */
@Module(includes = {NetworkModule.class})
public class ApiModule {

    /* api */

    @Inject
    @Provides
    TestApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(TestApiService.class);
    }

}
