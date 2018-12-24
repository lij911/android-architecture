package com.lijing.dev.storage;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author lijing
 */
public class IoUtils {
    /**
     * 简化 Closeable 的 close 方法
     *
     * @param closeable
     */
    public static void safeClose(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
