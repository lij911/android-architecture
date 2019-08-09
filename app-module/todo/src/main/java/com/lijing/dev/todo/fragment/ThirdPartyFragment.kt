package com.lijing.dev.todo.fragment

import android.arch.lifecycle.ViewModelProvider
import com.lijing.dev.mvvm.core.BaseAbstractFragment
import com.lijing.dev.todo.R

class ThirdPartyFragment : BaseAbstractFragment<ThirdPartyViewModel>() {
    override fun getViewModel(): ThirdPartyViewModel {
        return ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ThirdPartyViewModel::class.java)
    }

    override fun getContentLayoutID(): Int = R.layout.fragment_third_party_content

    override fun subscribeViewModel() {
    }

    override fun initVariables() {
    }

    override fun initViewsAndEvents() {
    }
}

