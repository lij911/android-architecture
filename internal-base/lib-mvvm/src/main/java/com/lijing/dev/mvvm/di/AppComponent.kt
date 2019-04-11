package com.lijing.dev.mvvm.di

import android.content.Context
import com.lijing.dev.core.annotation.AppScope
import com.lijing.dev.network.di.BaseApiComponent
import com.lijing.dev.utils.ContextUtils
import dagger.Component

@AppScope
@Component(dependencies = [BaseApiComponent::class], modules = [AppModule::class])
abstract class AppComponent {

    companion object {
        val instance = DaggerAppComponent
                .builder()
                .appModule(AppModule(ContextUtils.getContext()))
                .baseApiComponent(BaseApiComponent.getInstance())
                .build()
    }

    abstract fun provideContext(): Context
}