package com.lijing.dev.arch

import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.lijing.dev.arch.di.DaggerActivityComponent
import com.lijing.dev.core.RouterConstants
import com.lijing.dev.mvvm.core.BaseAbstractActivity
import com.lijing.dev.mvvm.di.AppComponent
import javax.inject.Inject
import kotlin.concurrent.thread

@Route(path = RouterConstants.App.ACTIVITY_SPLASH)
class SplashActivity : BaseAbstractActivity<SplashViewModel>() {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun getViewModel(): SplashViewModel {
        return splashViewModel
    }

    override fun getContentLayoutID(): Int {
        return R.layout.activity_splash
    }

    override fun subscribeViewModel() {
        DaggerActivityComponent
                .builder().appComponent(AppComponent.instance).build()
                .inject(this)
    }

    override fun initVariables() {
    }

    override fun initViewsAndEvents() {
        thread {
            Thread.sleep(1000)
            ARouter.getInstance().build(RouterConstants.Todo.ACTIVITY_MAIN).navigation()
        }
    }

}
