package com.lijing.dev.network;

import com.lijing.dev.network.di.DaggerApiComponent;
import com.lijing.dev.network.di.NetworkModule;

import org.junit.Test;

import javax.inject.Inject;

import io.reactivex.Observable;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Inject
    TestApiService mTestApiService;

    @Test
    public void networkTest(){
        Observable<Object> test = mTestApiService.test();
    }
}