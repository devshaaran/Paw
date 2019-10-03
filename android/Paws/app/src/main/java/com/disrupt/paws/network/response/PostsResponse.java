package com.disrupt.paws.network.response;

import com.disrupt.paws.model.Post;

import java.util.List;

public class PostsResponse {

    private List<Post> posts;

    public PostsResponse(List<Post> posts) {
        this.posts = posts;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

}
