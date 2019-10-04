package com.disrupt.paws.dependency_injection;

import androidx.lifecycle.ViewModel;

import com.disrupt.paws.ui.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ProfileViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindFProfileViewModel(ProfileViewModel viewModel);
}
