package com.ge.exchange.service;

import com.ge.exchange.model.Post;
import com.ge.exchange.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public String deletePost(int id) {
        postRepository.deleteById(id);
        return "Post removed!";
    }
    public Post findPostById(int id) {
        Post post = postRepository.findById(id).orElse(null);
        return post;
    }

    public Post updatePost(Post post) {
        Post existingPost = postRepository.findById(post.getPostId()).orElse(null);
        existingPost.setTitle(post.getTitle());
        existingPost.setCategory(post.getCategory());
        existingPost.setDate(post.getDate());
        existingPost.setContent(post.getContent());
        existingPost.setAuthor(post.getAuthor());
        return postRepository.save(existingPost);
    }
}
