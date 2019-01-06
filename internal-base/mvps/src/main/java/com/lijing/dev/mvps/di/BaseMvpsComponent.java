package com.lijing.dev.mvps.di;


import com.lijing.dev.core.annotation.BasisScope;
import com.lijing.dev.network.di.BaseApiComponent;

import dagger.Component;


/**
 * @author lijing
 */
@BasisScope
@Component(dependencies = {BaseApiComponent.class})
public abstract class BaseMvpsComponent implements MvpsComponent {

    @SuppressWarnings("StaticInitializerReferencesSubClass")
    private static final BaseMvpsComponent OUR_INSTANCE = DaggerBaseMvpsComponent.builder()
            .baseApiComponent(BaseApiComponent.getInstance())
            .build();


    public static BaseMvpsComponent getInstance() {
        return OUR_INSTANCE;
    }

}
