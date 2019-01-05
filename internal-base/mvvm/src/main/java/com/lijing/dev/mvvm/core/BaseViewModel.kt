package com.lijing.dev.mvvm.core

import android.arch.lifecycle.ViewModel
import com.lijing.dev.mvvm.live.SingleLiveEvent
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class BaseViewModel : ViewModel() {

    val hudEvent = SingleLiveEvent<Boolean>()

    override fun onCleared() {
        super.onCleared()
        TODO("do clear")
    }

    @Suppress("NOTHING_TO_INLINE")
    inline fun <T> Observable<T>.call(observer: Observer<in T>) {
        observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }

}