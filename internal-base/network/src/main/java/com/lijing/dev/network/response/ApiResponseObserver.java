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

    abstract protected void onSuccess(T t);

    abstract protected void onError(Exception e);

    abstract protected void onComplete(boolean isSuccess);

}
