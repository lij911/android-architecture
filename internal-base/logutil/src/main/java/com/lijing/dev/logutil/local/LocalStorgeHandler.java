package com.lijing.dev.logutil.local;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.lijing.dev.utils.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class LocalStorgeHandler extends Handler {

    public static final int PUSH = 1;
    public static final int FLUSH = 2;
    public static final int LOG_POOL_SIZE = 40;
    public static final int MMAP_SIZE = 64 * 1024;

    private File mLogFile;

    public LocalStorgeHandler(Looper looper) {
        super(looper);
        mLogFile = FileUtils.ExternalStorage.getCustomDir("log");
    }

    Queue<String> mLogs = new LinkedList<>();

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case PUSH: {
                push(msg);
                break;
            }
            case FLUSH: {
                break;
            }
            default:
                break;
        }
    }

    private void push(Message msg) {
        String log = (String) msg.obj;
        if (log == null || log.isEmpty()) {
            return;
        }
        mLogs.offer(log);
        if (mLogs.size() > LOG_POOL_SIZE) {
            flush();
        }
    }

    private void flush() {
        if (mLogs.isEmpty()) {
            return;
        }
        LinkedList<String> copy = new LinkedList<>();
        copy.addAll(mLogs);
        mLogs.clear();
        write(copy);
    }

    /**
     * 写入文件
     *
     * @param logs
     */
    private void write(LinkedList<String> logs) {
        if (mLogFile == null) {
            return;
        }
        // Try-with-resources
        try (
                FileOutputStream outputStream = new FileOutputStream(mLogFile, true);
                OutputStreamWriter writer = new OutputStreamWriter(outputStream, "utf-8")
        ) {
            for (String log : logs) {
                writer.write(log);
            }
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
