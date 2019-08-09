package com.lijing.dev.todo.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.support.v7.widget.LinearLayoutManager
import com.lijing.dev.mvvm.core.BaseAbstractFragment
import com.lijing.dev.todo.R
import kotlinx.android.synthetic.main.fragment_framework_content.*

class BasicFragment : BaseAbstractFragment<BasicViewModel>() {

    override fun getViewModel(): BasicViewModel {
        return ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(BasicViewModel::class.java)
    }

    override fun getContentLayoutID(): Int = R.layout.fragment_framework_content

    override fun subscribeViewModel() {
        getViewModel().liveBasicTitles.observe(this, Observer { data ->
            basicAdapter?.addItems(data)
        })
    }

    var basicAdapter: BasicAdapter? = null
    override fun initVariables() {
        basicAdapter = BasicAdapter()
    }

    override fun initViewsAndEvents() {
        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        linearLayoutManager.recycleChildrenOnDetach = true
        rv_basic_list.layoutManager = linearLayoutManager
        rv_basic_list.adapter = basicAdapter
        basicAdapter?.addOnItemClickListener { viewHolder, view, position ->

        }
    }

    override fun idleTask() {
        getViewModel().requestBasicTitles()
    }
}