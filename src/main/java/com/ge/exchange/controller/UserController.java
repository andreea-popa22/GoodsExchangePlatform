package com.ge.exchange.controller;

import com.ge.exchange.exception.ResourceNotFoundException;
import com.ge.exchange.model.User;
import com.ge.exchange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

//    @GetMapping("/login")
//    public String displayLogin(Model model){
//        model.addAttribute("username", "");
//        model.addAttribute("password", "");
//        return "login";
//    }
//    @PostMapping("/register")
//    public ResponseEntity<User> registerUserAccount(@RequestBody @Valid User user) {
//        User savedUser = userService.save(user);
//        savedUser.setUserId(user.getUserId());
//        return ResponseEntity.ok().body(savedUser);
//    }
//
//    @PutMapping("/update")
//    public User updateUser(@RequestBody User user) {
//        return userService.update(user);
//    }
}