package com.disrupt.paws.network;

import com.disrupt.paws.network.response.PostsResponse;

import retrofit2.Call;

public interface PawsApi {
    Call<PostsResponse> getPosts(String searchQuery, Integer second);
}
