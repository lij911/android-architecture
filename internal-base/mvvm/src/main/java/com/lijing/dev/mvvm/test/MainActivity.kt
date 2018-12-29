package com.lijing.dev.mvvm.test

import android.widget.Button
import com.lijing.dev.mvvm.R
import com.lijing.dev.mvvm.core.BaseAbstractActivity
import com.lijing.dev.mvvm.di.DaggerMvvmComponent
import com.lijing.dev.network.di.BaseApiComponent

class MainActivity : BaseAbstractActivity<MainViewModel>() {

    override fun getContentLayoutID(): Int {
        return R.layout.activity_main
    }

    override fun bindViewModel() {
        DaggerMvvmComponent.builder()
                .baseApiComponent(BaseApiComponent.getInstance())
                .build().inject(this)
    }

    override fun initVariables() {
    }

    override fun initViewsAndEvents() {
        findViewById<Button>(R.id.btn_show)
                .setOnClickListener { viewModel.doSomeThing() }
    }

}
