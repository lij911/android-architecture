package com.lijing.dev.network.di;

import com.lijing.dev.network.test.TestPresenter;
import com.lijing.dev.network.test.TestPresenter2;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author lijing
 */
public interface ApiComponent {

    void inject(TestPresenter testPresenter);

    void inject(TestPresenter2 testPresenter2);
}
