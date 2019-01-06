package com.lijing.dev.network.di;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lijing.dev.network.BuildConfig;
import com.lijing.dev.network.GeneralParams;
import com.lijing.dev.network.Interceptor.BasicParamsInterceptor;
import com.lijing.dev.network.NetworkConstant;
import com.lijing.dev.core.annotation.ApiScope;
import com.lijing.dev.network.converter.CustomGsonConverterFactory;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @author lijing
 */
@Module
public class NetworkModule {

    @Provides
    @ApiScope
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor, BasicParamsInterceptor basicParamsInterceptor) {


        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(NetworkConstant.DEFAULT_CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .readTimeout(NetworkConstant.DEFAULT_READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .writeTimeout(NetworkConstant.DEFAULT_WRITE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor);
        if (basicParamsInterceptor != null) {
            builder.addInterceptor(basicParamsInterceptor);
        }

        return builder.build();
    }

    @Provides
    @ApiScope
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit
                .Builder()
                .client(okHttpClient)
                .baseUrl(NetworkConstant.DOMAIN_URL)
                .addConverterFactory(CustomGsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @ApiScope
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return httpLoggingInterceptor;
    }

    @Provides
    @ApiScope
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    @ApiScope
    BasicParamsInterceptor provideBasicParamsInterceptor() {
        return new BasicParamsInterceptor.Builder()
                .addFilterUrls(GeneralParams.FILTER_URLS)
                .addHeaderParamsMap(GeneralParams.HEADER_PARAMS)
                .addBodyParamsMap(GeneralParams.BODY_PARAMS)
                .addQueryParamsMap(GeneralParams.QUERY_PARAMS)
                .build();
    }
}
