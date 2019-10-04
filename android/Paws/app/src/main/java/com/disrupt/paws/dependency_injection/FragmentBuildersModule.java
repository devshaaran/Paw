package com.disrupt.paws.dependency_injection;

import com.disrupt.paws.ui.explore.ExploreFragment;
import com.disrupt.paws.ui.feed.FeedFragment;
import com.disrupt.paws.ui.profile.ProfileFragment;
import com.disrupt.paws.ui.social.SocialFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector(
            modules = {PostsFeedModule.class,
                    PostsFeedViewModelModule.class}
    )
    abstract FeedFragment contributeFeedFragment();

    @ContributesAndroidInjector(
            modules = {ExploreViewModelModule.class}
    )
    abstract ExploreFragment contributeExploreFragment();

    @ContributesAndroidInjector(
            modules = {SocialViewModelModule.class}
    )
    abstract SocialFragment contributeSocialFragment();

    @ContributesAndroidInjector(
            modules = {ProfileViewModelModule.class}
    )
    abstract ProfileFragment contributeProfileFragment();
}
