package com.lijing.dev.todo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import org.jetbrains.annotations.Nullable;

/**
 * @author lijing
 */
public class StartableService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int times = 0;
                while (times < 5) {
                    try {
                        Thread.sleep(500);
                        Log.e("StartableService", "run: " + times);
                        times++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return;
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("StartableService can't be bind");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
