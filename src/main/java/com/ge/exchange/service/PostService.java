package com.ge.exchange.service;

import com.ge.exchange.dto.PostDto;
import com.ge.exchange.model.Post;

import java.util.List;

public interface PostService {
    public Post savePost(Post post);
    public List<PostDto> getAllPosts();
    public Post updatePost(Post post);
    public String deletePost(int id);
    public Post findPostById(int id);
}
