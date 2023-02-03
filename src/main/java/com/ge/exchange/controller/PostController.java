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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/post")
@CrossOrigin
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

//    @GetMapping("/")
//    public List<PostDto> list(Principal principal, Model model){
//        var a = principal;
//        User user = userService.findByEmail(a.getName());
//        return postRepository.getPostsOfOthers(user.getUserId());
//    }

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
