package com.ge.exchange.controller;


import com.ge.exchange.dto.PostDto;
import com.ge.exchange.exception.ResourceNotFoundException;
import com.ge.exchange.model.Post;
import com.ge.exchange.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@CrossOrigin
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/")
    public List<PostDto> list(){
        return postService.getAllPosts();
    }

    @PostMapping("/")
    public String add(@RequestBody Post post){
        postService.savePost(post);
        return "Post added";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> get(@PathVariable(value = "id") int id) throws ResourceNotFoundException {
        try {
            Post post = postService.findPostById(id);
            return ResponseEntity.ok().body(post);
        }
        catch (Exception e) {
            throw new ResourceNotFoundException("Post not found with this id: " + id);
        }
    }

    @PutMapping("/")
    public Post updatePost(@RequestBody Post post) {
        return postService.updatePost(post);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        return postService.deletePost(id);
    }
}
