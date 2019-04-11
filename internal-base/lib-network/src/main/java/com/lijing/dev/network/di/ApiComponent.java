package com.lijing.dev.network.di;

import com.lijing.dev.network.test.TestApiService;

/**
 * @author lijing
 */
public interface ApiComponent {

    TestApiService provideApiService();

}
