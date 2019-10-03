package com.disrupt.paws.dependency_injection;

import com.bumptech.glide.RequestManager;
import com.disrupt.paws.network.PawsApi;
import com.disrupt.paws.ui.feed.pagination.ItemAdapter;
import com.disrupt.paws.ui.feed.pagination.PostsDataSource;
import com.disrupt.paws.ui.feed.pagination.PostsDataSourceFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class PostsFeedModule {

    @Provides
    static ItemAdapter provideItemAdapter(RequestManager requestManager) {
        return new ItemAdapter(requestManager);
    }

    @Provides
    static PostsDataSource providePostsDataSource(PawsApi pawsApi) {
        return new PostsDataSource(pawsApi);
    }

    @Provides
    static PostsDataSourceFactory providePostsDataSourceFactory(PostsDataSource photosDataSource) {
        return new PostsDataSourceFactory(photosDataSource);
    }
}

