package com.disrupt.paws.dependency_injection;

import com.disrupt.paws.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = {}
    )
    abstract MainActivity contributeMainActivity();
}