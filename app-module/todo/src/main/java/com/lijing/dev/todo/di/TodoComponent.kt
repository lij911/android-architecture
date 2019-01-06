package com.lijing.dev.todo.di

import com.lijing.dev.core.annotation.ActivityScope
import com.lijing.dev.mvvm.di.BaseAppComponent
import com.lijing.dev.todo.MainActivity

import dagger.Component

@ActivityScope
@Component(dependencies = [BaseAppComponent::class])
interface TodoComponent {
    fun inject(mainActivity: MainActivity)
}
