package com.lijing.dev.network.response

import io.reactivex.observers.DisposableObserver

class KApiResponseObserver<T> : DisposableObserver<T>() {

    private var success: ((T) -> Unit)? = null
    private var complete: (() -> Unit)? = null
    private var error: ((Exception) -> Unit)? = null

    override fun onComplete() {
        complete?.invoke()
    }

    override fun onNext(value: T) {
        success?.invoke(value)
        onComplete()
    }


    override fun onError(e: Throwable?) {
        when (e) {
            null -> error?.invoke(UnsupportedOperationException("Throwable is null"))
            is Exception -> error?.invoke(e)
            else -> error?.invoke(UnsupportedOperationException("Throwable is unknown"))
        }
        onComplete()
    }

    fun onSuccess(block: (T) -> Unit): KApiResponseObserver<T> {
        success = block
        return this
    }

    fun onError(block: (Exception) -> Unit): KApiResponseObserver<T> {
        error = block
        return this
    }

    fun onComplete(block: () -> Unit): KApiResponseObserver<T> {
        complete = block
        return this
    }
}