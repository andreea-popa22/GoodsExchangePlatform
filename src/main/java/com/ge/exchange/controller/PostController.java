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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Autowired
    private PostMapper postMapper;

    @GetMapping("/new")
    public String add(Model model, Principal principal) throws ResourceNotFoundException {
        List<String> postCategories = Stream.of(PostCategory.values())
                .map(Enum::name)
                .collect(Collectors.toList());

//        PostDto postDto = new PostDto();
//        postDto.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
//        UserDto user = userService.findUserByEmail(principal.getName());
//        postDto.setAuthorId(user.getUserId());
        model.addAttribute("postCategories", postCategories);
        model.addAttribute("formData", new PostDto());
        return "newPost";
    }

    @PostMapping("/")
    public String add(@Valid @ModelAttribute("formData") PostDto postDto, BindingResult bindingResult, Principal principal) throws ResourceNotFoundException {
        postDto.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        UserDto user = userService.findUserByEmail(principal.getName());
        postDto.setAuthorId(user.getUserId());
        if (bindingResult.hasErrors()) {
            return "redirect:/post/new";
        }
        Post post = postMapper.fromPostDto(postDto);
        postService.savePost(post);
        return "redirect:/post/" + post.getPostId();
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

    @RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
    @ResponseBody
    public String deleteProduct(@PathVariable(value = "id") int id) {
         postService.deletePost(id);
         return "profile";
    }
}
