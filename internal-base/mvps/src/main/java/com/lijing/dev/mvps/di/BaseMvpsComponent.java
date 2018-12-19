package com.lijing.dev.mvps.di;


import com.lijing.dev.network.anno.PreActivity;
import com.lijing.dev.network.di.BaseApiComponent;

import dagger.Component;


/**
 * @author lijing
 */
@PreActivity
@Component(dependencies = {BaseApiComponent.class})
public abstract class BaseMvpsComponent implements MvpsComponent {

    private static final BaseMvpsComponent OUR_INSTANCE = DaggerBaseMvpsComponent
            .builder()
            .baseApiComponent(BaseApiComponent.getInstance())
            .build();

    public static BaseMvpsComponent getInstance() {
        return OUR_INSTANCE;
    }

}
