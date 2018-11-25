package com.lijing.dev.network.test;

import com.lijing.dev.network.di.DaggerApiComponent;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class TestPresenter {

    @Inject
    TestApiService mTestApiService;

    public TestPresenter() {
        DaggerApiComponent.builder().build().inject(this);
    }

    public void test() {
        Observable<Object> test = mTestApiService.test();
    }
}
