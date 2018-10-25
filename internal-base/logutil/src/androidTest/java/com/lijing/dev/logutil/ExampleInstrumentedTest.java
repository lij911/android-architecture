package com.lijing.dev.logutil;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.lijing.dev.utils.ContextUtils;
import com.orhanobut.logger.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;


import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        ContextUtils.setContext(appContext);
        myTest();
        assertEquals("com.lijing.dev.logutil.test", appContext.getPackageName());
    }

    private void myTest() {
        Logger.addLogAdapter(new LJLoggerAdapter());
        for (int i = 0; i < 100; i++) {
            Logger.d("com.lijing.dev.logutil.test：logger" + i);
        }
    }
}
