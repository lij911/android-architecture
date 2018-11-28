package com.lijing.dev.network.test;

import com.lijing.dev.network.di.DaggerApiComponent;
import com.lijing.dev.network.response.ApiResponseObserver;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class TestPresenter {

    @Inject
    TestApiService mTestApiService;
    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();


    public TestPresenter() {
        DaggerApiComponent.builder().build().inject(this);
    }

    public void test() {
        ApiResponseObserver<Object> observer = new ApiResponseObserver<Object>() {
            @Override
            protected void onSuccess(Object o) {

            }

            @Override
            protected void onError(Exception e) {
            }

            @Override
            protected void onComplete(boolean isSuccess) {

            }
        };
        mTestApiService.test().subscribe(observer);
        mCompositeDisposable.add(observer);
    }
}
