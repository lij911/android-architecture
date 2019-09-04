package com.lijing.dev.todo.fragment

import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import com.lijing.dev.mvvm.core.BaseAbstractFragment
import com.lijing.dev.todo.R
import com.lijing.dev.todo.clear.BActivity
import kotlinx.android.synthetic.main.fragment_third_party_content.*

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
        tv_jump.setOnClickListener {
            Intent(activity, BActivity::class.java).let {
                startActivity(it)
            }
        }
    }
}

