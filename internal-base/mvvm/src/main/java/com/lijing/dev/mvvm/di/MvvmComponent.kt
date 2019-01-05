package com.lijing.dev.mvvm.di

import com.lijing.dev.network.annotation.ActivityScope
import com.lijing.dev.network.di.BaseApiComponent
import dagger.Component


@ActivityScope
@Component(dependencies = [BaseApiComponent::class])
interface MvvmComponent {
}