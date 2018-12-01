package com.lijing.dev.utils;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

import java.lang.ref.WeakReference;

import javax.xml.transform.sax.SAXTransformerFactory;

/**
 * 全局 context 的工具类
 *
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
     *
     * @return
     */
    public static Context getContext() {
        return sContextWeakReference.get();
    }

    /**
     * 注入 LeakCanary
     *
     * @param application
     */
    public static void injectLeakCanary(Application application) {
        if (LeakCanary.isInAnalyzerProcess(application)) {
            return;
        }
        LeakCanary.install(application);
    }

}
