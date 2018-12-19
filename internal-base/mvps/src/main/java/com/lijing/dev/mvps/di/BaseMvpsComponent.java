package com.lijing.dev.mvps.di;


import com.lijing.dev.network.di.ApiComponent;
import com.lijing.dev.network.di.PreActivity;

import dagger.Component;


/**
 * @author lijing
 */
@PreActivity
@Component(dependencies = {ApiComponent.class})
public abstract class MvpsComponent {

    @SuppressWarnings("StaticInitializerReferencesSubClass")
    private static final MvpsComponent OUR_INSTANCE = DaggerMvpsComponent.builder().build();

    public static MvpsComponent getInstance() {
        return OUR_INSTANCE;
    }
}
