package com.disrupt.paws.dependency_injection;

import androidx.lifecycle.ViewModel;

import com.disrupt.paws.ui.feed.FeedViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class PostsFeedViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FeedViewModel.class)
    public abstract ViewModel bindFeedViewModel(FeedViewModel viewModel);
}
