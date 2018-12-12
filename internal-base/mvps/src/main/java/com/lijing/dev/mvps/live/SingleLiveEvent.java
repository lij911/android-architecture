package com.lijing.dev.mvps.live;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.util.Log;

import org.jetbrains.annotations.Nullable;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author lijing
 */
public class SingleLiveEvent<T> extends LiveData<T> {

    private static final String TAG = SingleLiveEvent.class.getSimpleName();

    private final AtomicBoolean mPending = new AtomicBoolean(false);

    @Override
    @MainThread
    public void observe(@NonNull LifecycleOwner owner, @NonNull final Observer<T> observer) {

        if (hasActiveObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.");
        }

        super.observe(owner, new Observer<T>() {
            @Override
            public void onChanged(@android.support.annotation.Nullable T t) {
                if (mPending.compareAndSet(true, false)) {
                    observer.onChanged(t);
                }
            }
        });
    }

    @MainThread
    @Override
    public void setValue(@Nullable T value) {
        mPending.set(true);
        super.setValue(value);
    }

    @Override
    public void postValue(T value) {
        mPending.set(true);
        super.postValue(value);
    }

    @MainThread
    public void call() {
        setValue(null);
    }
}
