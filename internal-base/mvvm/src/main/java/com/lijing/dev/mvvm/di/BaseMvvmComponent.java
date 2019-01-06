package com.lijing.dev.mvvm.di;

import com.lijing.dev.core.annotation.BasisScope;
import com.lijing.dev.network.di.BaseApiComponent;

import dagger.Component;

/**
 * @author lijing
 */
@BasisScope
@Component(dependencies = {BaseApiComponent.class})
public abstract class BaseMvvmComponent implements MvvmComponent {

    @SuppressWarnings("StaticInitializerReferencesSubClass")
    private static final BaseMvvmComponent OUR_INSTANCE = DaggerBaseMvvmComponent.builder()
            .baseApiComponent(BaseApiComponent.getInstance())
            .build();

    public static BaseMvvmComponent getInstance() {
        return OUR_INSTANCE;
    }
}
