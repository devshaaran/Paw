package com.disrupt.paws.network;

import com.disrupt.paws.model.MapMarker;
import com.disrupt.paws.network.response.PostsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PawsApi {

    @GET("posts/{user_id}/{page}")
    Call<PostsResponse> getPosts(@Path("user_id") long userId, @Path("page") int page);

    @GET("getvet/{vet_id}")
    Call<MapMarker> getLocation(@Path("vet_id") int page);
}
