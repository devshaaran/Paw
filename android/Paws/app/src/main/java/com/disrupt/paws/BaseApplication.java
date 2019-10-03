package com.disrupt.paws;

import com.disrupt.paws.dependency_injection.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class BaseApplication extends DaggerApplication {

    private static BaseApplication application;

    public void onCreate() {
        super.onCreate();
        application = this;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

    public static BaseApplication getInstance() {
        return application;
    }
}
