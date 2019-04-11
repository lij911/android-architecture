package com.lijing.dev.arch

import android.content.Context
import android.support.multidex.MultiDexApplication
import com.alibaba.android.arouter.launcher.ARouter
import com.lijing.dev.utils.ContextUtils

class KApplication : MultiDexApplication() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        setupInternalConfig()
        setupExternalConfig()
    }

    /**
     * 初始化自己的库
     */
    private fun setupInternalConfig() {
        ContextUtils.setContext(this)
    }

    /**
     * 初始化第三方的库
     */
    private fun setupExternalConfig() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }
}