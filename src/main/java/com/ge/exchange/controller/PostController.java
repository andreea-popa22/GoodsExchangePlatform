package com.ge.exchange.controller;


import com.ge.exchange.dto.PostDto;
import com.ge.exchange.dto.UserDto;
import com.ge.exchange.exception.ResourceNotFoundException;
import com.ge.exchange.mappers.PostMapper;
import com.ge.exchange.mappers.UserMapper;
import com.ge.exchange.model.Post;
import com.ge.exchange.model.PostCategory;
import com.ge.exchange.model.User;
import com.ge.exchange.repository.PostRepository;
import com.ge.exchange.service.PostService;
import com.ge.exchange.service.UserService;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping(value = "/post", headers = "Connection!=Upgrade")
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

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/new")
    public String add(Model model, Principal principal) throws ResourceNotFoundException {
        List<String> postCategories = Stream.of(PostCategory.values())
                .map(Enum::name)
                .collect(Collectors.toList());

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

    @GetMapping("/{id}/chat")
    public String displayChat(@PathVariable(value = "id") int id, Model model) {
        model.addAttribute("sender", SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("receiver", postService.findPostById(id).getAuthor().getEmail());
        return "chat";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable(value = "id") int id, Model model, Principal principal) throws ResourceNotFoundException {
        try {
            Boolean isAuthor = false;
            UserDto currentUserDto = userService.findUserByEmail(principal.getName());
            Integer currentUserId = currentUserDto.getUserId();
            Post post = postService.findPostById(id);
            if (Objects.equals(post.getAuthor().getUserId(), currentUserId)){
                isAuthor = true;
            }

            User user = userMapper.fromUserDto(currentUserDto);

            List<String> titles = user.getPosts().stream().map(Post::getTitle).collect(Collectors.toList());
            model.addAttribute("postsToChoose", titles);
            model.addAttribute("isAuthor", isAuthor);
            model.addAttribute("post", post);
            model.addAttribute("postId", id);
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
    public ResponseEntity<Void> deletePost(Principal principal,@PathVariable(value = "id") int id) throws ResourceNotFoundException {
        String a = principal.getName();
        UserDto user = userService.findUserByEmail(a);
        postService.deletePost(id);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/{id}")
                .buildAndExpand(user.getUserId().toString()).toUri();
        return ResponseEntity.status(HttpStatus.FOUND).location(location).build();
    }

}
