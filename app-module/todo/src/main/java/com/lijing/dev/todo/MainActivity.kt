package com.lijing.dev.todo

import android.support.v7.widget.Toolbar
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.lijing.dev.core.RouterConstants
import com.lijing.dev.mvvm.core.ToolBarActivity
import com.lijing.dev.mvvm.di.AppComponent
import com.lijing.dev.todo.di.DaggerTodoComponent
import com.lijing.dev.utils.BitmapUtils
import com.lijing.dev.utils.DensityUtil
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@Route(path = RouterConstants.Todo.ACTIVITY_MAIN)
class MainActivity : ToolBarActivity<MainViewModel>() {

    override val menuRes: Int? get() = R.menu.toolbar

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

    }

    override fun setupToolBar(toolbar: Toolbar) {
        initStatusBar(R.color.res_black)

    }

}
