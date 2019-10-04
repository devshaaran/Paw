package com.disrupt.paws.dependency_injection;

import androidx.lifecycle.ViewModel;
import com.disrupt.paws.ui.social.SocialViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class SocialViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SocialViewModel.class)
    public abstract ViewModel bindSocialViewModel(SocialViewModel viewModel);
}
