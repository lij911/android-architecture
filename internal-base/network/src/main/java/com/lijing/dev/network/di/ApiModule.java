package com.lijing.dev.network.di;


import com.lijing.dev.network.annotation.ApiScope;
import com.lijing.dev.network.test.TestApiService;

import javax.inject.Singleton;

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

    @ApiScope
    @Provides
    public TestApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(TestApiService.class);
    }

}
