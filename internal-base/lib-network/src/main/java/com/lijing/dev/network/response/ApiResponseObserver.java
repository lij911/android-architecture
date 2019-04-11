package com.lijing.dev.network.response;

import io.reactivex.observers.DisposableObserver;

/**
 * @author lijing
 */
public abstract class ApiResponseObserver<T> extends DisposableObserver<T> {

    private static final String TAG = ApiResponseObserver.class.getSimpleName();

    @Override
    public void onNext(T t) {
        onSuccess(t);
        onComplete(true);
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof Exception) {
            onError((Exception) e);
        } else {
            onError(new UnsupportedOperationException("接口请求异常", e));
        }
        onComplete(false);
    }

    @Override
    public void onComplete() {
        onComplete(true);
    }

    /**
     * 成功后调用的方法
     * @param response
     */
    abstract protected void onSuccess(T response);

    /**
     * 请求失败后调用的方法
     * @param e
     */
    abstract protected void onError(Exception e);

    /**
     * 请求完成后调用的方法
     * @param isSuccess 是否成功
     */
    abstract protected void onComplete(boolean isSuccess);

}
