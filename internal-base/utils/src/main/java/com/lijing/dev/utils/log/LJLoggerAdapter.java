package com.lijing.dev.utils.log;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.orhanobut.logger.CsvFormatStrategy;
import com.orhanobut.logger.LogAdapter;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * @author lijing
 */
public class LJLoggerAdapter implements LogAdapter {

    private PrettyFormatStrategy mPrettyFormatStrategy;
    private CsvFormatStrategy mCsvFormatStrategy;


    public LJLoggerAdapter() {
        this.mPrettyFormatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .methodCount(2)
                .tag("LJLogger")
                .build();
        this.mCsvFormatStrategy = CsvFormatStrategy.newBuilder()
                .tag("LJLogger")
                .build();
    }

    @Override
    public boolean isLoggable(int priority, @Nullable String tag) {
        return true;
    }

    @Override
    public void log(int priority, @Nullable String tag, @NonNull String message) {
        mPrettyFormatStrategy.log(priority, tag, message);
        mCsvFormatStrategy.log(priority, tag, message);
    }
}
