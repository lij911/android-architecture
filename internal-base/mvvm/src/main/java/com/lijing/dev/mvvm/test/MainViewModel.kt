package com.lijing.dev.mvvm.test

import com.lijing.dev.mvvm.core.BaseViewModel
import javax.inject.Inject
import kotlin.concurrent.thread

class MainViewModel : BaseViewModel {

    @Inject
    constructor()

    fun doSomeThing() {
        thread {
            Thread.sleep(1 * 1000)
            hudEvent.postValue(true)
        }
    }
}