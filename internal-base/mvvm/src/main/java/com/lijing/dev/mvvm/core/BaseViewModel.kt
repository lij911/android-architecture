package com.lijing.dev.mvvm.core

import android.arch.lifecycle.ViewModel
import com.lijing.dev.mvvm.live.SingleLiveEvent

open class BaseViewModel : ViewModel() {

    val hudEvent = SingleLiveEvent<Boolean>()

    override fun onCleared() {
        super.onCleared()
        TODO("do clear")
    }
}