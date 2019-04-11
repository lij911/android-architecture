package com.lijing.dev.mvvm.core

import android.support.annotation.LayoutRes

interface IBaseActivity<VM : BaseViewModel> : IBaseView {

    /**
     * 获取 ViewModel
     */
    fun getViewModel(): VM

    /**
     * 绑定 layout id
     */
    @LayoutRes
    fun getContentLayoutID(): Int

    /**
     * 绑定 ViewModel
     */
    fun bindViewModel()

    /**
     * 初始化变量
     */
    fun initVariables()

    /**
     * 初始化界面
     */
    fun initViewsAndEvents()

}