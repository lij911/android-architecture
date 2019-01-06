package com.lijing.dev.todo

import com.alibaba.android.arouter.facade.annotation.Route
import com.lijing.dev.core.RouterConstants
import com.lijing.dev.mvvm.core.BaseAbstractActivity
import com.lijing.dev.mvvm.di.BaseAppComponent
import com.lijing.dev.todo.di.DaggerTodoComponent
import javax.inject.Inject

@Route(path = RouterConstants.ACTIVITY_TODO_MAIN)
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
        DaggerTodoComponent.builder().baseAppComponent(BaseAppComponent.getInstance()).build().inject(this)
    }

    override fun initVariables() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun initViewsAndEvents() {

    }

}
