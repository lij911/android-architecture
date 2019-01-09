package com.lijing.dev.arch.di

import com.lijing.dev.arch.SplashActivity
import com.lijing.dev.core.annotation.ActivityScope
import com.lijing.dev.mvvm.di.AppComponent
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class])
interface ActivityComponent {
    fun inject(splashActivity: SplashActivity)
}
