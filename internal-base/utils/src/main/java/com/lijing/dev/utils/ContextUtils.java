package com.lijing.dev.utils;

import android.content.Context;

import java.lang.ref.WeakReference;

/**
 * 全局获取 context 的工具类
 * @author lijing
 */
public class ContextUtils {

    private static WeakReference<Context> sContextWeakReference;

    private ContextUtils() {
    }

    /**
     * 初始化 Context，通常将 application 赋值给它
     *
     * @param context
     */
    public static synchronized void setContext(Context context) {
        if (sContextWeakReference == null) {
            sContextWeakReference = new WeakReference<>(context);
        }
    }

    /**
     * 获取 context
     * @return
     */
    public static Context getContext() {
        return sContextWeakReference.get();
    }
}
