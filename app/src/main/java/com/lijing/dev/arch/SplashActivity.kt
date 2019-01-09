package com.lijing.dev.arch

import android.os.Handler
import com.alibaba.android.arouter.launcher.ARouter
import com.lijing.dev.arch.di.DaggerActivityComponent
import com.lijing.dev.core.RouterConstants
import com.lijing.dev.mvvm.core.BaseAbstractActivity
import com.lijing.dev.mvvm.di.AppComponent
import javax.inject.Inject

class SplashActivity : BaseAbstractActivity<SplashViewModel>() {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun getViewModel(): SplashViewModel {
        return splashViewModel
    }

    override fun getContentLayoutID(): Int {
        return R.layout.activity_splash
    }

    override fun bindViewModel() {
        DaggerActivityComponent
                .builder().appComponent(AppComponent.instance).build()
                .inject(this)
    }

    override fun initVariables() {
    }

    override fun initViewsAndEvents() {
        ARouter.getInstance().build(RouterConstants.ACTIVITY_TODO_MAIN).navigation()
    }

}
