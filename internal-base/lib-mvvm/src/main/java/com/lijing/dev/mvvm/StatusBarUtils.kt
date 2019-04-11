package com.lijing.dev.mvvm

import android.annotation.TargetApi
import android.app.Activity
import android.os.Build
import android.support.annotation.ColorRes
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager

class StatusBarUtils {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun translucentStatusBarColor(activity: Activity, @ColorRes color: Int) {
        activity.window?.run {
            addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
        SystemBarTintManager(activity).run {
            isStatusBarTintEnabled = true
            setStatusBarTintResource(color)
        }
        fitsWindows(activity)
    }

    /**
     * api 19 以后的适配方式
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun modifyStatusBarColor(activity: Activity, @ColorRes color: Int) {
        activity.window?.run {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = ContextCompat.getColor(activity, color)
        }
        fitsWindows(activity)
    }

    private fun fitsWindows(activity: Activity) {
        val childView = activity.findViewById<ViewGroup>(Window.ID_ANDROID_CONTENT).getChildAt(0)
        childView?.let {
            ViewCompat.setFitsSystemWindows(childView, true)
        }
    }
}