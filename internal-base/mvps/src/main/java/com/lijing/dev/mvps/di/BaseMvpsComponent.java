package com.lijing.dev.mvps.di;


import com.lijing.dev.network.annotation.ActivityScope;
import com.lijing.dev.network.di.BaseApiComponent;

import dagger.Component;


/**
 * @author lijing
 */
@ActivityScope
@Component(dependencies = {BaseApiComponent.class})
public abstract class BaseMvpsComponent implements MvpsComponent {

    //    private static final BaseMvpsComponent OUR_INSTANCE = DaggerBaseMvpsComponent
//            .builder()
//            .baseApiComponent(BaseApiComponent.getInstance())
//            .build();

    private static final BaseMvpsComponent OUR_INSTANCE = null;

    public static BaseMvpsComponent getInstance() {
        return OUR_INSTANCE;
    }

}
