package com.lijing.dev.todo.di;

import com.lijing.dev.mvvm.di.BaseMvvmComponent;
import com.lijing.dev.network.annotation.ActivityScope;
import com.lijing.dev.network.di.BaseApiComponent;

import dagger.Component;

/**
 * @author lijing
 */
@ActivityScope
@Component(dependencies = {BaseMvvmComponent.class})
public abstract class BaseTodoComponent implements TodoComponent {

    @SuppressWarnings("StaticInitializerReferencesSubClass")
    private static final BaseTodoComponent INSTANCE = DaggerBaseTodoComponent.builder()
            .baseMvvmComponent(BaseMvvmComponent.getInstance())
            .build();

    public static BaseTodoComponent getInstance() {
        return INSTANCE;
    }

}
