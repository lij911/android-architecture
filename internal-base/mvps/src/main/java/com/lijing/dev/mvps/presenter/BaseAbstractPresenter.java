package com.lijing.dev.mvps.presenter;

import android.os.Bundle;

import com.lijing.dev.mvps.IBaseView;
import com.lijing.dev.network.response.ApiResponseObserver;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author lijing
 */
public abstract class BaseAbstractPresenter<V extends IBaseView> implements IBasePresenter {

    protected V mView;
    protected CompositeDisposable mCompositeDisposable;



    public BaseAbstractPresenter() {
    }

    public final void attachView(V view) {
        mView = view;
    }

    public final void detachView() {
        mView = null;
    }

    public final void onSaveInstanceState(Bundle outState) {

    }

    public final V getView() {
        return mView;
    }


    private void subscribe(Disposable disposable) {
        if (disposable == null) {
            return;
        }
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void subscribe(Observable observable, ApiResponseObserver abstractApiResponseObserver) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(abstractApiResponseObserver);
        subscribe(abstractApiResponseObserver);
    }

    @Override
    public void unsubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
            mCompositeDisposable = null;
        }
    }

}
