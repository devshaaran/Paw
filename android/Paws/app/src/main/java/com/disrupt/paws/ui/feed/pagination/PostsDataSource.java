package com.disrupt.paws.ui.feed.pagination;

import android.util.Log;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.disrupt.paws.model.Post;
import com.disrupt.paws.network.PawsApi;
import com.disrupt.paws.network.response.PostsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsDataSource extends PageKeyedDataSource<Pair<String, Integer>, Post> {

    private static final String TAG = "PostsDataSource";

    private final static int FIRST_PAGE = 1;

    private final PawsApi pawsApi;

    private String searchQuery;

    public PostsDataSource(PawsApi pawsApi, String searchQuery) {
        this.pawsApi = pawsApi;
        this.searchQuery = searchQuery;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Pair<String, Integer>> params, @NonNull final LoadInitialCallback<Pair<String, Integer>, Post> callback) {
        if (searchQuery != null) {
            pawsApi.getPosts(searchQuery, FIRST_PAGE).enqueue(new Callback<PostsResponse>() {
                @Override
                public void onResponse(Call<PostsResponse> call, Response<PostsResponse> response) {
                    PostsResponse body = response.body();
                    if (body != null && body.getPosts() != null) {
                        //callback.onResult(response.body().getPosts().getPost(), null, new Pair<>(searchQuery, FIRST_PAGE + 1));
                    }
                }

                @Override
                public void onFailure(Call<PostsResponse> call, Throwable t) {
                    Log.e(TAG, t.getMessage());
                }
            });
        }
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Pair<String, Integer>> params, @NonNull final LoadCallback<Pair<String, Integer>, Post> callback) {
        if (searchQuery != null) {
            pawsApi.getPosts(searchQuery, params.key.second).enqueue(new Callback<PostsResponse>() {
                @Override
                public void onResponse(Call<PostsResponse> call, Response<PostsResponse> response) {
                    Pair<String, Integer> previousKey = params.key.second > 1 ? new Pair<>(searchQuery, params.key.second - 1) : null;
                    if (response.body() != null) {
                        //callback.onResult(response.body().getPosts().getPost(), previousKey);
                    }
                }

                @Override
                public void onFailure(Call<PostsResponse> call, Throwable t) {
                    Log.e(TAG, t.getMessage());
                }
            });
        }
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Pair<String, Integer>> params, @NonNull final LoadCallback<Pair<String, Integer>, Post> callback) {
        if (searchQuery != null) {
            pawsApi.getPosts(searchQuery, params.key.second).enqueue(new Callback<PostsResponse>() {
                @Override
                public void onResponse(Call<PostsResponse> call, Response<PostsResponse> response) {
                    int totalPages;
                    PostsResponse body = response.body();
                    if (body != null) {
                        //totalPages = body.posts.getPages();
                        //Pair<String, Integer> previousKey = params.key.second < totalPages ? new Pair<>(searchQuery, params.key.second + 1) : null;
                        //callback.onResult(body.getPosts().getPost(), previousKey);
                    }
                }

                @Override
                public void onFailure(Call<PostsResponse> call, Throwable t) {
                    Log.e(TAG, t.getMessage());
                }
            });
        }
    }
}
