package com.lijing.dev.mvps.presenter;


import com.lijing.dev.network.response.ApiResponseObserver;

import io.reactivex.Observable;

/**
 * @author lijing
 */
public interface IBasePresenter {


    /**
     * 注册请求
     * @param observable
     * @param abstractApiResponseObserver
     */
    void subscribe(Observable observable, ApiResponseObserver abstractApiResponseObserver);

    /**
     * 取消注册
     */
    void unsubscribe();

}
