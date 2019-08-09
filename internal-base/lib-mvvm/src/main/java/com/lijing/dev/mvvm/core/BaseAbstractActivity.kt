package com.lijing.dev.mvvm.core

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import com.kaopiz.kprogresshud.KProgressHUD


abstract class BaseAbstractActivity<VM : BaseViewModel> : AppCompatActivity(), IBaseView<VM> {

    private val hud: KProgressHUD by lazy {
        KProgressHUD
                .create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentLayoutID())
        subscribeViewModel()
        attachViewModelInternal(getViewModel())
        initVariables()
        initViewsAndEvents()
        enqueueIdleTask()
    }


    /**
     * 内部去绑定 ViewModel
     */
    fun attachViewModelInternal(viewModel: BaseViewModel) {
        viewModel.hudEvent.observe(this, Observer { b -> showHud(b ?: false) })
    }

    override fun showHud(show: Boolean) {
        if (hud.isShowing && !show) {
            hud.dismiss()
        } else if (!hud.isShowing && show) {
            hud.show()
        }
    }


    private fun enqueueIdleTask() {
        Looper.myQueue().addIdleHandler {
            idleTask()
            return@addIdleHandler false
        }
    }

    /**
     * 使用 idle handler 在主线程空闲时调用
     */
    open fun idleTask() {}

}