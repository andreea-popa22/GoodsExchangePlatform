package com.ge.exchange.controller;

import com.ge.exchange.dto.PostDto;
import com.ge.exchange.model.Post;
import com.ge.exchange.model.User;
import com.ge.exchange.service.PostService;
import com.ge.exchange.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping
public class HomeController {

    private final UserService userService;
    private final PostService postService;

    public HomeController(UserService userService, PostService postService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/home")
    public String displayHome(Principal principal, Model model) {
        var a = principal.getName();
        List<PostDto> posts = postService.getAllPosts();

        model.addAttribute("email", a);
        model.addAttribute("posts", posts);
        return "home";
    }
}


