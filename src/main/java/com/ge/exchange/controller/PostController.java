package com.ge.exchange.controller;


import com.ge.exchange.dto.PostDto;
import com.ge.exchange.exception.ResourceNotFoundException;
import com.ge.exchange.model.Post;
import com.ge.exchange.model.User;
import com.ge.exchange.repository.PostRepository;
import com.ge.exchange.service.PostService;
import com.ge.exchange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/post")
@CrossOrigin
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public String add(@RequestBody Post post){
        postService.savePost(post);
        return "Post added";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable(value = "id") int id, Model model) throws ResourceNotFoundException {
        try {
            Post post = postService.findPostById(id);
            model.addAttribute("post", post);
            return "post";
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
