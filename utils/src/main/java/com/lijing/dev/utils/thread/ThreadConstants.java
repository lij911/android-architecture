package com.lijing.dev.utils.thread;

public interface ThreadConstants {

    /**
     * CPU 数量
     */
    int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    /**
     * 异步的 Thread Pool 配置
     * 参考 AsyncTask 源码
     */
    interface Async {
        /**
         * 核心线程数，2 - 4 个，同时保证小于 （CPU_COUNT - 1）这个值，以避免 cpu 饱和
         */
        int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
        /**
         * 最大线程数
         */
        int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
        /**
         * CPU 空闲后，线程存活的时间
         */
        int KEEP_ALIVE_SECONDS = 30;
    }

    /**
     * 同步的 Thread Pool 配置
     * 参考 ImageLoad 源码
     */
    interface Sync {
        /**
         * 核心线程数
         */
        int CORE_POOL_SIZE = 0;
        /**
         * 最大线程数
         */
        int MAXIMUM_POOL_SIZE = 1;
        /**
         * CPU 空闲后，线程存活的时间，下载等任务往往有更大的间隔时间
         */
        int KEEP_ALIVE_SECONDS = 60;
    }
}
