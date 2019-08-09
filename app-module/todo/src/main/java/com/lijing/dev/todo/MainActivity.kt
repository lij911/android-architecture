package com.lijing.dev.todo

import android.arch.lifecycle.ViewModelProvider
import android.support.v7.widget.Toolbar
import com.alibaba.android.arouter.facade.annotation.Route
import com.lijing.dev.core.RouterConstants
import com.lijing.dev.mvvm.core.ToolBarActivity
import com.lijing.dev.todo.fragment.BasicFragment
import com.lijing.dev.todo.fragment.ThirdPartyFragment
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = RouterConstants.Todo.ACTIVITY_MAIN)
class MainActivity : ToolBarActivity<MainViewModel>() {

    override val menuRes: Int? get() = R.menu.toolbar

    override fun getViewModel(): MainViewModel {
        return ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
    }

    override fun getContentLayoutID(): Int = R.layout.activity_main

    override fun setupToolBar(toolbar: Toolbar) {
        initStatusBar(R.color.res_black)
    }

    override fun subscribeViewModel() {

    }

    var mainPagerAdapter: MainPagerAdapter? = null
    override fun initVariables() {
        mainPagerAdapter = MainPagerAdapter(supportFragmentManager, arrayOf(BasicFragment(), ThirdPartyFragment()))
    }

    override fun initViewsAndEvents() {
        viewPager.adapter = mainPagerAdapter
    }

}
