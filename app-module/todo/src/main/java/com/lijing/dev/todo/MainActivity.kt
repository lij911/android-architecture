package com.lijing.dev.todo

import com.alibaba.android.arouter.facade.annotation.Route
import com.lijing.dev.core.RouterConstants
import com.lijing.dev.mvvm.core.BaseAbstractActivity
import com.lijing.dev.mvvm.di.AppComponent
import com.lijing.dev.todo.di.DaggerTodoComponent
import com.lijing.dev.utils.BitmapUtils
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@Route(path = RouterConstants.Todo.ACTIVITY_MAIN)
class MainActivity : BaseAbstractActivity<MainViewModel>() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun getViewModel(): MainViewModel {
        return mainViewModel
    }

    override fun getContentLayoutID(): Int {
        return R.layout.activity_main
    }

    override fun bindViewModel() {
        DaggerTodoComponent.builder()
                .appComponent(AppComponent.instance).build()
                .inject(this)
    }

    override fun initVariables() {
    }

    override fun initViewsAndEvents() {
        val url = "https://b-gold-cdn.xitu.io/v3/static/img/android.fef4da1.png"
        BitmapUtils.loadImage(url) { iv_image.setImageBitmap(it) }
    }

}
