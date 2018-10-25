package com.lijing.dev.logutil;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.lijing.dev.logutil.local.LocalStorageThread;
import com.lijing.dev.utils.ContextUtils;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.LogAdapter;
import com.orhanobut.logger.PrettyFormatStrategy;

import java.util.Objects;

/**
 * @author lijing
 */
public class LJLoggerAdapter implements LogAdapter {

    @NonNull
    private final FormatStrategy formatStrategy;

    private LocalStorageThread localStorageThread;

    public LJLoggerAdapter() {
        this.formatStrategy = PrettyFormatStrategy
                .newBuilder()
                .showThreadInfo(true)
                .methodCount(2)
                .tag(ContextUtils.getContext().getString(R.string.logger_tag))
                .build();
        localStorageThread = new LocalStorageThread();
    }

    public LJLoggerAdapter(@NonNull FormatStrategy formatStrategy) {
        this.formatStrategy = Objects.requireNonNull(formatStrategy);
    }

    @Override
    public boolean isLoggable(int priority, @Nullable String tag) {
//        return BuildConfig.DEBUG;
        return true;
    }

    @Override
    public void log(int priority, @Nullable String tag, @NonNull String message) {
        formatStrategy.log(priority, tag, message);
        localStorageThread.pushLog(tag + ": " + message);
    }
}
