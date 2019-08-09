package com.lijing.dev.mvvm.core

import android.content.Context
import android.os.Bundle
import android.os.Looper
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseAbstractFragment<VM : BaseViewModel> : Fragment(), IBaseView<VM> {

    /**
     * 与 Activity 关联
     **/
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        subscribeViewModel()
        attachViewModelInternal()
    }

    private fun attachViewModelInternal() {
        if (activity is BaseAbstractActivity<*>) {
            (activity as BaseAbstractActivity<*>).attachViewModelInternal(getViewModel())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initVariables()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getContentLayoutID(), null, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewsAndEvents()
        enqueueIdleTask()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun showHud(show: Boolean) {
        if (activity is BaseAbstractActivity<*>) {
            (activity as BaseAbstractActivity<*>).showHud(show)
        }
    }


    private fun enqueueIdleTask() {
        Looper.myQueue().addIdleHandler {
            idleTask()
            return@addIdleHandler false
        }
    }

    /**
     * 使用 idle handler 在主线程空闲时调用
     */
    open fun idleTask() {}

}