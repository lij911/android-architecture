package com.lijing.dev.todo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import org.jetbrains.annotations.Nullable;

/**
 * @author lijing
 */
public class BindableService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private InternalBinder mBinder = new InternalBinder();

    public class InternalBinder extends Binder {

    }
}
