package com.lijing.dev.utils

import android.content.Context
import java.lang.ref.WeakReference

object ContextUtils {

    private var weakReference: WeakReference<Context>? = null

    @JvmStatic
    fun setContext(context: Context) {
        weakReference = WeakReference(context)
    }

    @JvmStatic
    fun getContext(): Context? {
        return weakReference?.get()
    }


}