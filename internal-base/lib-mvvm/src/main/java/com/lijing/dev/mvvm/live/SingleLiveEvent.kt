package com.lijing.dev.mvvm.live

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.support.annotation.MainThread
import android.util.Log

import java.util.concurrent.atomic.AtomicBoolean

/**
 * @author lijing
 */
class SingleLiveEvent<T> : LiveData<T>() {

    private val staticTag = "SingleLiveEvent"
    private val mPending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<T>) {

        if (hasActiveObservers()) {
            Log.w(staticTag, "Multiple observers registered but only one will be notified of changes.")
        }

        super.observe(owner, Observer { t ->
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }

    @MainThread
    public override fun setValue(value: T?) {
        mPending.set(true)
        super.setValue(value)
    }

    public override fun postValue(value: T) {
        mPending.set(true)
        super.postValue(value)
    }

    @MainThread
    public fun call() {
        value = null
    }
}
