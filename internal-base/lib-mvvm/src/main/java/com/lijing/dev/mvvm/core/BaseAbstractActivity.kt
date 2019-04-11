package com.lijing.dev.mvvm.core

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.v7.app.AppCompatActivity
import com.kaopiz.kprogresshud.KProgressHUD


abstract class BaseAbstractActivity<VM : BaseViewModel> : AppCompatActivity(), IBaseActivity<VM> {

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
        bindViewModel()
        attachViewModel()
        initVariables()
        initViewsAndEvents()
//        Looper.myQueue().addIdleHandler { lazyLoad() }
    }

    private fun attachViewModel() {
        getViewModel().hudEvent.observe(this, Observer { b -> showHud(b ?: false) })
    }

    override fun showHud(show: Boolean) {
        if (hud.isShowing && !show) {
            hud.dismiss()
        } else if (!hud.isShowing && show) {
            hud.show()
        }
    }

    /**
     * 使用 idle handler 在主线程空闲时调用
     */
//     fun lazyLoad(): Boolean = Unit
//
//    @ColorInt
//    fun getColorInt(@ColorRes colorRes: Int): Int {
//        return resources.getColor(colorRes)
//    }
}