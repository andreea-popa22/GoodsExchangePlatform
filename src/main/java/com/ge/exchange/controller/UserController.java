package com.ge.exchange.controller;

import com.ge.exchange.dto.PostDto;
import com.ge.exchange.dto.UserDto;
import com.ge.exchange.exception.ResourceNotFoundException;
import com.ge.exchange.mappers.PostMapper;
import com.ge.exchange.model.Post;
import com.ge.exchange.model.Request;
import com.ge.exchange.model.User;
import com.ge.exchange.repository.PostRepository;
import com.ge.exchange.service.PostService;
import com.ge.exchange.service.RequestService;
import com.ge.exchange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/user", headers = "Connection!=Upgrade")
@CrossOrigin
public class UserController {

    private final UserService userService;
    private final PostService postService;
    private final RequestService requestService;
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public UserController(UserService userService, PostService postService, RequestService requestService, PostRepository postRepository, PostMapper postMapper) {
        this.userService = userService;
        this.postService = postService;
        this.requestService = requestService;
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }
    @GetMapping("/{id}/posts")
    public String displayProfilePosts(@PathVariable(value = "id") int id, Model model) throws ResourceNotFoundException {
        UserDto user = userService.findUserById(id);
        List<Post> posts = postRepository.getPostsOfUser(user.getUserId());

        List<PostDto> postDtos = posts.stream()
                .map(postMapper::toPostDto)
                .collect(Collectors.toList());
//        model.addAttribute("email", a);
        model.addAttribute("currentUser", user.getUserId());
        model.addAttribute("posts", posts);
        return "profilePosts.html";
    }

    @GetMapping("/requests")
    public String get(Model model, Principal principal) throws ResourceNotFoundException {
        try {
            UserDto user = userService.findUserByEmail(principal.getName());
            List<Request> requests = requestService.getReceivedRequests(user.getUserId());
            model.addAttribute("requests", requests);
            model.addAttribute("currentUser", user.getUserId());
            return "userRequests";
        }
        catch (Exception e) {
            throw new ResourceNotFoundException("Request not found with this email: " + principal.getName());
        }
    }

    @GetMapping("/{id}")
    public String displayProfile(@PathVariable(value = "id") int id, Model model) throws ResourceNotFoundException {
        UserDto user = userService.findUserById(id);
//        model.addAttribute("email", a);

        model.addAttribute("currentUser", user.getUserId());
        model.addAttribute("user", user);

        return "profile.html";
    }
}