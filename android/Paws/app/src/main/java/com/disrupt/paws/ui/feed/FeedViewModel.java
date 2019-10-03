package com.disrupt.paws.ui.feed;

import android.util.Pair;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.disrupt.paws.model.Post;
import com.disrupt.paws.ui.feed.pagination.PostsDataSourceFactory;
import com.disrupt.paws.util.Constants;

import javax.inject.Inject;

public class FeedViewModel extends ViewModel {

    private LiveData<PagedList<Post>> pagedListLiveData;
    private MutableLiveData<PageKeyedDataSource<Pair<Long, Integer>, Post>> pageKeyedDataSourceMutableLiveData;

    private PostsDataSourceFactory photosDataSourceFactory;

    @Inject
    FeedViewModel(PostsDataSourceFactory photosDataSourceFactory) {
        this.photosDataSourceFactory = photosDataSourceFactory;
        pageKeyedDataSourceMutableLiveData = photosDataSourceFactory.getPostsLiveDataSource();
        buildPagedListLiveData();
    }

    public void buildPagedListLiveData() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(Constants.PAGE_SIZE)
                .build();

        pagedListLiveData = new LivePagedListBuilder<>(photosDataSourceFactory, config).build();
    }

    public void setPagedListLiveData(LiveData<PagedList<Post>> pagedListLiveData) {
        this.pagedListLiveData = pagedListLiveData;
    }

    public LiveData<PagedList<Post>> getPagedListLiveData() {
        return pagedListLiveData;
    }

    public PostsDataSourceFactory getPostsDataSourceFactory() {
        return photosDataSourceFactory;
    }

    public MutableLiveData<PageKeyedDataSource<Pair<Long, Integer>, Post>> getPageKeyedDataSourceMutableLiveData() {
        return pageKeyedDataSourceMutableLiveData;
    }
}