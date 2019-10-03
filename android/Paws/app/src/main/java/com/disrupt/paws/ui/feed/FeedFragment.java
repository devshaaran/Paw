package com.disrupt.paws.ui.feed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.disrupt.paws.R;
import com.disrupt.paws.dependency_injection.ViewModelProviderFactory;
import com.disrupt.paws.model.Post;
import com.disrupt.paws.network.PawsApi;
import com.disrupt.paws.ui.feed.pagination.ItemAdapter;
import com.disrupt.paws.ui.feed.pagination.PostsDataSource;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class FeedFragment extends DaggerFragment {

    private FeedViewModel feedViewModel;
    @Inject
    public ItemAdapter adapter;
    @Inject
    public PawsApi pawsApi;
    @Inject
    public ViewModelProviderFactory viewModelProviderFactory;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        feedViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(FeedViewModel.class);
        View root = inflater.inflate(R.layout.fragment_feed, container, false);
        initRecyclerView(root);
        initPost(root);
        refreshList();
        return root;
    }

    private void initRecyclerView(View root) {
        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void initPost(View root) {

    }

    private void refreshList() {
        PostsDataSource postsDataSource = new PostsDataSource(pawsApi);
        feedViewModel.getPageKeyedDataSourceMutableLiveData().setValue(postsDataSource);
        feedViewModel.getPostsDataSourceFactory().setPostsDataSource(postsDataSource);
        feedViewModel.buildPagedListLiveData();
        feedViewModel.getPagedListLiveData().observe(this, new Observer<PagedList<Post>>() {
            @Override
            public void onChanged(PagedList<Post> posts) {
                adapter.submitList(posts);
            }
        });
    }
}