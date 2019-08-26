package com.lijing.dev.todo.fragment

import android.arch.lifecycle.MutableLiveData
import com.lijing.dev.mvvm.core.BaseViewModel

class BasicViewModel : BaseViewModel() {

    val liveBasicTitles = MutableLiveData<List<String>>()

    fun requestBasicTitles() {
        liveBasicTitles.value = listOf("Activity","Service","Broadcast")
    }
}