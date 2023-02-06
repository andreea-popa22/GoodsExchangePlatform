package com.ge.exchange.controller;


import com.ge.exchange.dto.UserDto;
import com.ge.exchange.exception.ResourceNotFoundException;
import com.ge.exchange.mappers.UserMapper;
import com.ge.exchange.model.*;
import com.ge.exchange.repository.ExchangeRepository;
import com.ge.exchange.repository.NotificationRepository;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private ExchangeRepository exchangeRepository;

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

    @PostMapping("/accept")
    public String acceptRequest(@RequestParam int requesterPostId, @RequestParam int receiverPostId,
                                @RequestParam int requestId) throws ResourceNotFoundException {
        String content = "Your request for '" + postRepository.findById(requesterPostId).get().getTitle() + "' post has been accepted.";
        UserDto userDto = userService.findUserById(postRepository.findById(requesterPostId).get().getAuthor().getUserId());
        User user = userMapper.fromUserDto(userDto);
        Notification notification = new Notification(content, user);
        notificationRepository.save(notification);
        
        if (postRepository.findById(requesterPostId).isEmpty() || postRepository.findById(receiverPostId).isEmpty()){
            throw new ResourceNotFoundException("One of the posts in the request has been deleted.");
        }

        Request request = requestRepository.findById(requestId).get();
        Exchange exchange = new Exchange(request, Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()), "Location", Status.ACCEPTED);
        exchangeRepository.save(exchange);

        postRepository.delete(postRepository.findById(requesterPostId).get());
        postRepository.delete(postRepository.findById(receiverPostId).get());
        //requestRepository.delete(requestRepository.findById(requestId).get());
        return "redirect:/home";
    }

    @PostMapping("/decline")
    public String declineRequest(@RequestParam int requestId, @RequestParam int requesterPostId) throws ResourceNotFoundException {
        // TODO send notification to requester
        String content = "Your request for '" + postRepository.findById(requesterPostId).get().getTitle() + "' post has been declined.";
        UserDto userDto = userService.findUserById(postRepository.findById(requesterPostId).get().getAuthor().getUserId());
        User user = userMapper.fromUserDto(userDto);
        Notification notification = new Notification(content, user);
        notificationRepository.save(notification);

        Request request = requestRepository.findById(requestId).get();
        Exchange exchange = new Exchange(request, null, "", Status.DECLINED);
        exchangeRepository.save(exchange);

        requestRepository.delete(requestRepository.findById(requestId).get());
        return "redirect:/home";
    }
}
