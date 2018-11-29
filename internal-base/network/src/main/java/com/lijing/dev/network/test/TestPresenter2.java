package com.lijing.dev.network.test;

import com.lijing.dev.network.di.DaggerBaseApiComponent;
import com.lijing.dev.network.response.ApiResponseModel;
import com.lijing.dev.network.response.ApiResponseObserver;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class TestPresenter2 {

    @Inject
    public TestApiService mTestApiService;

    protected CompositeDisposable mCompositeDisposable = new CompositeDisposable();


    public TestPresenter2() {
        DaggerBaseApiComponent.create().inject(this);
    }

    public void test() {
        ApiResponseObserver<ApiResponseModel<String>> observer = new ApiResponseObserver<ApiResponseModel<String>>() {

            @Override
            protected void onSuccess(ApiResponseModel<String> stringApiResponseModel) {
                System.out.print(stringApiResponseModel.getData());
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
