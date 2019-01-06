package com.lijing.dev.network.di;

import com.lijing.dev.core.annotation.ApiScope;

import dagger.Component;

/**
 * @author lijing
 */
@ApiScope
@Component(modules = {ApiModule.class})
public abstract class BaseApiComponent implements ApiComponent {

    @SuppressWarnings("StaticInitializerReferencesSubClass")
    private static final BaseApiComponent OUR_INSTANCE = DaggerBaseApiComponent.create();

    public static BaseApiComponent getInstance() {
        return OUR_INSTANCE;
    }

}
