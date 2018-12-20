package com.lijing.dev.utils.thread.factory;


import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 下载所用到的 ThreadFactory
 * @author lijing
 */
public class CustomThreadFactory implements ThreadFactory {

    private String tag;
    private AtomicInteger mAtomicInteger = new AtomicInteger(1);


    public CustomThreadFactory(String tag) {
        this.tag = tag;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread();
        thread.setName(tag + "_" + mAtomicInteger.getAndIncrement());
        return thread;
    }
}
