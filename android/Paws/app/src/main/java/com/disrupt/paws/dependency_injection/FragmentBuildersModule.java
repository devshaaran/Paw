package com.disrupt.paws.dependency_injection;

import com.disrupt.paws.ui.feed.FeedFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector(
            modules = {PostsFeedModule.class,
                    PostsFeedViewModelModule.class}
    )
    abstract FeedFragment contributeFeedFragment();
}
