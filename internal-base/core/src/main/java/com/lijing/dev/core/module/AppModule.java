package com.lijing.dev.core.module;

import android.content.Context;

import com.lijing.dev.core.annotation.AppScope;

import dagger.Module;
import dagger.Provides;

/**
 * @author lijing
 */

@Module
public class AppModule {

    private Context mContext;

    public AppModule(Context context) {
        mContext = context;
    }

    @Provides
    @AppScope
    public Context provideContext() {
        return mContext;
    }

}
