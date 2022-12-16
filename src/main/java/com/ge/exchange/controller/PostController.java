package com.ge.exchange.controller;


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

    @PostMapping("/add")
    public String add(@RequestBody Post post){
        postService.savePost(post);
        return "Post added";
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Post> get(@PathVariable(value = "id") int id) throws ResourceNotFoundException {
        try {
            Post post = postService.findPostById(id);
            return ResponseEntity.ok().body(post);
        }
        catch (Exception e) {
            throw new ResourceNotFoundException("Post not found with this id: " + id);
        }
    }

    @GetMapping("/getAll")
    public List<Post> list(){
        return postService.getAllPosts();
    }

    @PutMapping("/update")
    public Post updatePost(@RequestBody Post post) {
        return postService.updatePost(post);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        return postService.deletePost(id);
    }
}
