package com.ge.exchange.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String displayWelcome(){
        return "Welcome";
    }

    @GetMapping("/home")
    public String displayHome(){
        return "Home";
    }

}
