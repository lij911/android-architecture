package com.lijing.dev.network.di;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApiModule.class})
public abstract class BaseApiComponent implements ApiComponent {

    private static final BaseApiComponent ourInstance = DaggerBaseApiComponent.create();

    public static BaseApiComponent getInstance() {
        return ourInstance;
    }


}
