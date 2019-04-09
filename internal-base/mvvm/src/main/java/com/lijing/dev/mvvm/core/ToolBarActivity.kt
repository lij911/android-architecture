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
        toolbar = findViewById(R.id.action_bar)
        toolbar?.let {
            setSupportActionBar(it)
        }
    }

}