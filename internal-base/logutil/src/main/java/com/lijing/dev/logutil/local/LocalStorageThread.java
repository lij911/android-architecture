package com.lijing.dev.logutil.local;

import android.os.Looper;
import android.os.Message;

/**
 * @author lijing
 */
public class LocalStorageThread extends Thread {

    private LocalStorageHandler mStorageHandler;

    public LocalStorageThread() {
        Looper.prepare();
        mStorageHandler = new LocalStorageHandler(Looper.myLooper());
    }

    public void pushLog(String log) {
        Message message = Message.obtain();
        message.obj = log;
        message.what = LocalStorageHandler.PUSH;
        mStorageHandler.handleMessage(message);
    }
}
