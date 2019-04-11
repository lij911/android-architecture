package com.lijing.dev.network;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

    @Test
    public void networkTest() {
        TestPresenter testPresenter = new TestPresenter();
        testPresenter.test();

        TestPresenter2 testPresenter2 = new TestPresenter2();
        testPresenter2.test();

        if (testPresenter.mTestApiService == testPresenter.mTestApiService) {
            System.out.print(123);
        }
    }
}