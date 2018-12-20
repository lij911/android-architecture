package com.lijing.dev.utils

import android.content.Context
import android.content.SharedPreferences

object SpUtils {

    private var sharedPreferences: SharedPreferences? = ContextUtils.getContext()?.getSharedPreferences("DefaultPreference", Context.MODE_PRIVATE)

    @JvmStatic
    public fun getSharedPreferences(): SharedPreferences? {
        return sharedPreferences
    }

}


