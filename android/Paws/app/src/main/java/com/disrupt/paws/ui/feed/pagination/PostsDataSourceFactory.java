package com.disrupt.paws.ui.feed.pagination;

import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.disrupt.paws.model.Post;

public class PostsDataSourceFactory extends DataSource.Factory<Pair<String, Integer>, Post> {

    private MutableLiveData<PageKeyedDataSource<Pair<String, Integer>, Post>> photosLiveDataSource = new MutableLiveData<>();

    private PostsDataSource photosDataSource;

    public PostsDataSourceFactory(PostsDataSource photosDataSource) {
        this.photosDataSource = photosDataSource;
    }

    @NonNull
    @Override
    public DataSource<Pair<String, Integer>, Post> create() {
        photosLiveDataSource.postValue(photosDataSource);
        return photosDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Pair<String, Integer>, Post>> getPostsLiveDataSource() {
        return photosLiveDataSource;
    }

    public void setPostsDataSource(PostsDataSource photosDataSource) {
        this.photosDataSource = photosDataSource;
    }

    public PostsDataSource getPostsDataSource() {
        return photosDataSource;
    }
}