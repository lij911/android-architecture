package com.lijing.dev.mvvm.di;

import com.lijing.dev.core.annotation.BasisScope;
import com.lijing.dev.network.di.BaseApiComponent;

import dagger.Component;

/**
 * @author lijing
 */
@BasisScope
@Component(dependencies = {BaseApiComponent.class})
public abstract class BaseAppComponent implements AppComponent {

    @SuppressWarnings("StaticInitializerReferencesSubClass")
    private static final BaseAppComponent OUR_INSTANCE = DaggerBaseAppComponent.builder()
            .baseApiComponent(BaseApiComponent.getInstance())
            .build();

    public static BaseAppComponent getInstance() {
        return OUR_INSTANCE;
    }

}
