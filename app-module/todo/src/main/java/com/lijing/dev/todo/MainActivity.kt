package com.lijing.dev.todo

import android.arch.lifecycle.ViewModelProvider
import android.support.v4.app.Fragment
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

    var mainPagerAdapter: MainPagerAdapter? = null

    var basicFragment = lazy { BasicFragment() }
    var thirdPartyFragment = lazy { ThirdPartyFragment() }

    override fun getViewModel(): MainViewModel {
        return ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
    }

    override fun getContentLayoutID(): Int = R.layout.activity_main

    override fun setupToolBar(toolbar: Toolbar) {
        initStatusBar(R.color.res_black)
    }

    override fun subscribeViewModel() {
    }

    override fun initVariables() {
        mainPagerAdapter = MainPagerAdapter(supportFragmentManager, arrayOf(basicFragment.value as Fragment, thirdPartyFragment.value as Fragment))
    }

    override fun initViewsAndEvents() {
        viewPager.adapter = mainPagerAdapter
    }

}
