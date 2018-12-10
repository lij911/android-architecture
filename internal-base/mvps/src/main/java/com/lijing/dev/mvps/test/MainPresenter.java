package com.lijing.dev.mvps.test;

import android.widget.Toast;

import com.lijing.dev.mvps.R;
import com.lijing.dev.mvps.presenter.BaseAbstractPresenter;
import com.lijing.dev.network.response.ApiResponseModel;
import com.lijing.dev.network.response.ApiResponseObserver;
import com.lijing.dev.network.test.TestApiService;

import javax.inject.Inject;

public class MainPresenter extends BaseAbstractPresenter<MainActivity> {
    @Inject
    public TestApiService mTestApiService;

    public void test(){
        ApiResponseObserver<ApiResponseModel<String>> observer = new ApiResponseObserver<ApiResponseModel<String>>() {

            @Override
            protected void onSuccess(ApiResponseModel<String> response) {
            }

            @Override
            protected void onError(Exception e) {

            }

            @Override
            protected void onComplete(boolean isSuccess) {

            }
        };
        subscribe(mTestApiService.test(), observer);
    }
}
