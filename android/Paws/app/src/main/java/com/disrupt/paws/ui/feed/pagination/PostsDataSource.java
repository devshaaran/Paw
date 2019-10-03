package com.disrupt.paws.ui.feed.pagination;

import android.util.Log;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.disrupt.paws.model.Post;
import com.disrupt.paws.network.PawsApi;
import com.disrupt.paws.network.response.PostsResponse;
import com.disrupt.paws.util.MiscUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.disrupt.paws.util.Constants.CURRENT_USER;

public class PostsDataSource extends PageKeyedDataSource<Pair<Long, Integer>, Post> {

    private static final String TAG = "PostsDataSource";

    private final static int FIRST_PAGE = 1;

    private final PawsApi pawsApi;

    public PostsDataSource(PawsApi pawsApi) {
        this.pawsApi = pawsApi;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Pair<Long, Integer>> params, @NonNull final LoadInitialCallback<Pair<Long, Integer>, Post> callback) {
        String json = MiscUtil.loadJSONFromAsset("paws-d3ecc-export.json");
        PostsResponse response = (PostsResponse) MiscUtil.stringToJson(json, PostsResponse.class);
        callback.onResult(response.getPosts(), null, new Pair<>(CURRENT_USER, FIRST_PAGE + 1));
        /*
        pawsApi.getPosts(CURRENT_USER, FIRST_PAGE).enqueue(new Callback<PostsResponse>() {
            @Override
            public void onResponse(Call<PostsResponse> call, Response<PostsResponse> response) {
                PostsResponse body = response.body();
                if (body != null && body.getPosts() != null) {
                    callback.onResult(response.body().getPosts(), null, new Pair<>(CURRENT_USER, FIRST_PAGE + 1));
                }
            }

            @Override
            public void onFailure(Call<PostsResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
        */
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Pair<Long, Integer>> params, @NonNull final LoadCallback<Pair<Long, Integer>, Post> callback) {
        String jsonNumber = MiscUtil.getJsonNumber(params.key.second);
        String json = MiscUtil.loadJSONFromAsset("paws-d3ecc-export.json");
        PostsResponse response = (PostsResponse) MiscUtil.stringToJson(json, PostsResponse.class);
        Pair<Long, Integer> previousKey = params.key.second > 1 ? new Pair<>(CURRENT_USER, params.key.second - 1) : null;
        callback.onResult(response.getPosts(), previousKey);
        /*
        pawsApi.getPosts(CURRENT_USER, params.key.second).enqueue(new Callback<PostsResponse>() {
            @Override
            public void onResponse(Call<PostsResponse> call, Response<PostsResponse> response) {
                Pair<Long, Integer> previousKey = params.key.second > 1 ? new Pair<>(CURRENT_USER, params.key.second - 1) : null;
                if (response.body() != null) {
                    callback.onResult(response.body().getPosts(), previousKey);
                }
            }

            @Override
            public void onFailure(Call<PostsResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
        */
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Pair<Long, Integer>> params, @NonNull final LoadCallback<Pair<Long, Integer>, Post> callback) {
        String jsonNumber = MiscUtil.getJsonNumber(params.key.second);
        String json = MiscUtil.loadJSONFromAsset("paws-d3ecc-export.json");
        PostsResponse response = (PostsResponse) MiscUtil.stringToJson(json, PostsResponse.class);
        int totalPages = 1;
        Pair<Long, Integer> previousKey = params.key.second < totalPages ? new Pair<>(CURRENT_USER, params.key.second + 1) : null;
        callback.onResult(response.getPosts(), previousKey);
        /*
        pawsApi.getPosts(CURRENT_USER, params.key.second).enqueue(new Callback<PostsResponse>() {
            @Override
            public void onResponse(Call<PostsResponse> call, Response<PostsResponse> response) {
                int totalPages;
                PostsResponse body = response.body();
                if (body != null) {
                    totalPages = body.getPages();
                    Pair<Long, Integer> previousKey = params.key.second < totalPages ? new Pair<>(CURRENT_USER, params.key.second + 1) : null;
                    callback.onResult(body.getPosts(), previousKey);
                }
            }

            @Override
            public void onFailure(Call<PostsResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
        */
    }
}
