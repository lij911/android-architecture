package com.lijing.dev.storage;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.lijing.dev.utils.ContextUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

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
        String absolutePath = FileUtils.InternalStorage.INSTANCE.getFilesDir().getAbsolutePath();
        String absolutePath1 = FileUtils.InternalStorage.INSTANCE.getCacheDir().getAbsolutePath();
        assertEquals("com.lijing.dev.storage.test", appContext.getPackageName());
    }

}
