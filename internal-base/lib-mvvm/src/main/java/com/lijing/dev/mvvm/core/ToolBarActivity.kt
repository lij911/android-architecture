package com.lijing.dev.mvvm.core

import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.lijing.dev.mvvm.R

/**
 * 带 toolbar 的 activity
 *
 */
abstract class ToolBarActivity<VM : BaseViewModel> : BaseAbstractActivity<VM>() {

    var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolbar()
    }

    private fun initToolbar() {
        toolbar = findViewById(R.id.toolbar)
        toolbar?.let {
            setSupportActionBar(it)
            setupToolBar(it)
        }
    }

    /**
     * 用于子类初始化 toolbar
     */
    abstract fun setupToolBar(toolbar: Toolbar)

}