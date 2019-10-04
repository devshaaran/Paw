package com.disrupt.paws.dependency_injection;

import androidx.lifecycle.ViewModel;

import com.disrupt.paws.ui.explore.ExploreViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ExploreViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ExploreViewModel.class)
    public abstract ViewModel bindFeedViewModel(ExploreViewModel viewModel);
}
