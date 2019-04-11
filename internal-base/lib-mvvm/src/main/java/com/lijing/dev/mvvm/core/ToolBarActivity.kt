package com.lijing.dev.mvvm.core

import android.os.Build
import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.lijing.dev.mvvm.R
import com.lijing.dev.mvvm.StatusBarUtils

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

    /**
     * 修改状态栏
     */
    protected fun initStatusBar(@ColorRes colorRes: Int) {
        val statusBarUtils = StatusBarUtils()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            statusBarUtils.modifyStatusBarColor(this, colorRes)
        } else {
            statusBarUtils.translucentStatusBarColor(this, colorRes)
        }
    }

    /**
     * 在子类中重写 menuRes 的 get 方法
     */
    open val menuRes: Int? = null

    final override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuRes?.let {
            menuInflater.inflate(it, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }


    /**
     * 在子类重写 mItemSelected 的 get 方法
     */
    open val mItemSelected: ((itemId: Int?) -> Unit)? = null

    final override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        mItemSelected?.invoke(item?.itemId)
        return super.onOptionsItemSelected(item)

    }
}