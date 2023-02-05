package com.ge.exchange.controller;


import com.ge.exchange.exception.ResourceNotFoundException;
import com.ge.exchange.mappers.UserMapper;
import com.ge.exchange.model.Post;
import com.ge.exchange.model.Request;
import com.ge.exchange.model.User;
import com.ge.exchange.repository.PostRepository;
import com.ge.exchange.repository.RequestRepository;
import com.ge.exchange.service.RequestService;
import com.ge.exchange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.PrinterURI;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/request", headers = "Connection!=Upgrade")
@CrossOrigin
public class RequestController {
    @Autowired
    private RequestService requestService;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/new")
    public String add(@Valid @ModelAttribute("postToChoose") String title, Principal principal, @RequestParam int postId) throws ResourceNotFoundException {
        Optional<Post> requestedPost = postRepository.findById(postId);
        Optional<Post> chosenPost = postRepository.getPostByTitle(title);
        User requester = userMapper.fromUserDto(userService.findUserByEmail(principal.getName()));
        Request request = new Request(requester, requestedPost.get().getAuthor(), chosenPost.get(), requestedPost.get());
        requestRepository.save(request);
        return "redirect:/home";
    }

    @RequestMapping("/add")
    public String add(@RequestBody Request request){
        requestService.saveRequest(request);
        return "Request added";
    }



    @GetMapping("/getAll")
    public List<Request> list(){
        return requestService.getAllRequests();
    }

    @PutMapping("/update")
    public Request updateRequest(@RequestBody Request request) {
        return requestService.updateRequest(request);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        return requestService.deleteRequest(id);
    }
}
