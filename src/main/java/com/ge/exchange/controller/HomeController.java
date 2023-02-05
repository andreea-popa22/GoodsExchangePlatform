package com.ge.exchange.controller;

import com.ge.exchange.dto.PostDto;
import com.ge.exchange.dto.UserDto;
import com.ge.exchange.exception.ResourceNotFoundException;
import com.ge.exchange.mappers.PostMapper;
import com.ge.exchange.model.Post;
import com.ge.exchange.model.PostCategory;
import com.ge.exchange.model.User;
import com.ge.exchange.repository.PostRepository;
import com.ge.exchange.service.PostService;
import com.ge.exchange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping(headers = "Connection!=Upgrade")
public class HomeController {
    private final UserService userService;

    private final PostService postService;
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public HomeController(UserService userService, PostService postService, PostRepository postRepository, PostMapper postMapper) {
        this.userService = userService;
        this.postService = postService;
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @GetMapping("/home")
    public String displayHome(Principal principal, Model model) throws ResourceNotFoundException {
        String a = principal.getName();
        UserDto user = userService.findUserByEmail(a);
        List<Post> posts = postRepository.getPostsOfOthers(user.getUserId());

        // filter by city
        List<Post> filteredPosts = postService.filterPostsByUserCity(posts, user.getUserId());

        List<PostDto> postDtos = filteredPosts.stream()
                .map(postMapper::toPostDto)
                .collect(Collectors.toList());

        model.addAttribute("currentUser", user.getUserId());
        model.addAttribute("email", a);
        model.addAttribute("posts", postDtos);
        return "home";
    }
}


