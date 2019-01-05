package com.lijing.dev.utils

import android.content.Context
import java.lang.ref.WeakReference

object ContextUtils {

    private var weakReference: WeakReference<Context>? = null

    @JvmStatic
    public fun setContext(context: Context) {
        weakReference = WeakReference(context)
    }

    @JvmStatic
    public fun getContext(): Context? {
        return weakReference?.get()
    }

//    @JvmStatic
//    public fun initLeakCanary(context: Context?) {
//        if (context == null || LeakCanary.isInAnalyzerProcess(context)) {
//            return
//        }
//        if (context is Application) {
//            LeakCanary.install(context)
//        }
//    }

}