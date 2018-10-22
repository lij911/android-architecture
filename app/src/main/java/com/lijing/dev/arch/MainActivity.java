package com.lijing.dev.arch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.lijing.dev.utils.ContextUtils;
import com.lijing.dev.utils.FileUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContextUtils.setContext(this);
        setContentView(R.layout.activity_main);
        Log.e("FileUtilsTest", "FileUtilsTest: " + FileUtils.InternalStorage.getRootDir().getAbsolutePath());
        Log.e("FileUtilsTest", "FileUtilsTest: " + FileUtils.InternalStorage.getCacheDir().getAbsolutePath());
        Log.e("FileUtilsTest", "FileUtilsTest: " + FileUtils.ExternalStorage.SD_ROOT_DIR.getAbsolutePath());
    }
}
