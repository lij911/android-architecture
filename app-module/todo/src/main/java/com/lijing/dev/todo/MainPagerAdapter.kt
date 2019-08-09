package com.lijing.dev.todo

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class MainPagerAdapter(fm: FragmentManager?, private val fragments: Array<Fragment>) : FragmentStatePagerAdapter(fm) {

    override fun getItem(p0: Int): Fragment {
        return fragments.getOrNull(p0)
                ?: throw NullPointerException("MainPagerAdapter had null fragment")
    }

    override fun getCount(): Int {
        return fragments.size
    }


}