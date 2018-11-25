package com.lijing.dev.network.di;

import com.lijing.dev.network.test.TestPresenter;

import dagger.Component;

/**
 * @author lijing
 */
@Component(modules = {ApiModule.class})
public interface ApiComponent {

    void inject(TestPresenter testPresenter);
}
