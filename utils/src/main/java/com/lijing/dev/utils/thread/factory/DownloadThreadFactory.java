package com.lijing.dev.utils.thread.factory;

import android.support.annotation.NonNull;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 下载所用到的 ThreadFactory
 */
public class DownloadThreadFactory implements ThreadFactory {

    private String tag;
    private AtomicInteger mAtomicInteger = new AtomicInteger(1);


    public DownloadThreadFactory(String tag) {
        this.tag = tag;
    }

    @Override
    public Thread newThread(@NonNull Runnable runnable) {
        Thread thread = new Thread();
        thread.setName(tag + "_" + mAtomicInteger.getAndIncrement());
        return thread;
    }
}
