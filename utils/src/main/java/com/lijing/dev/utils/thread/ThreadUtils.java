package com.lijing.dev.utils.thread;

import android.util.TimeUtils;

import com.lijing.dev.utils.thread.factory.DownloadThreadFactory;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池工具类
 *
 * @author lijing
 */
public class ThreadUtils {


    /**
     * 同步的线程池
     *
     * @return
     */
    public static ThreadPoolExecutor allocSyncPool(String tag) {
        return new ThreadPoolExecutor(
                ThreadConstants.Sync.CORE_POOL_SIZE,
                ThreadConstants.Sync.MAXIMUM_POOL_SIZE,
                ThreadConstants.Sync.KEEP_ALIVE_SECONDS,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(),
                new DownloadThreadFactory(tag)
        );
    }

    /**
     * 异步的线程池
     *
     * @param tag
     * @return
     */
    public static ThreadPoolExecutor allocAsyncPool(String tag) {
        return new ThreadPoolExecutor(
                ThreadConstants.Async.CORE_POOL_SIZE,
                ThreadConstants.Async.MAXIMUM_POOL_SIZE,
                ThreadConstants.Async.KEEP_ALIVE_SECONDS,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(128),
                new DownloadThreadFactory(tag)
        );
    }

}
