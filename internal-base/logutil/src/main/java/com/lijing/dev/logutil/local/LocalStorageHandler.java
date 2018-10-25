package com.lijing.dev.logutil.local;


import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.lijing.dev.utils.FileUtils;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;

import okio.BufferedSink;
import okio.Okio;
import okio.Sink;

/**
 * @author lijing
 */
public class LocalStorageHandler extends Handler {

    public static final int PUSH = 1;
    public static final int FLUSH = 2;
    public static final int LOG_POOL_SIZE = 40;
    public static final int MMAP_SIZE = 64 * 1024;

    private File mLogFile;

    public LocalStorageHandler(Looper looper) {
        super(looper);
        mLogFile = FileUtils.InternalStorage.getCustomDir("log");
        Logger.i(mLogFile.getAbsolutePath());
    }

    LinkedBlockingDeque<String> mLogs = new LinkedBlockingDeque<>(LOG_POOL_SIZE * 2);

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
        mLogs.add(log);
        if (mLogs.size() > LOG_POOL_SIZE) {
            write();
        }
    }

    /**
     * 写入文件
     * 使用 okio
     */
    private void write() {
        if (mLogFile == null) {
            return;
        }
        try (
                Sink mSink = Okio.appendingSink(mLogFile);
                BufferedSink buffer = Okio.buffer(mSink);
        ) {
            while (!mLogs.isEmpty()) {
                buffer.writeUtf8(mLogs.pollFirst());
            }
            buffer.flush();
            mSink.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入文件
     * 使用 java 的I/O
     *
     * @param logs
     */
    @Deprecated
    private void write_v1(LinkedList<String> logs) {
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

    public void destroy() {
        write();
    }
}
