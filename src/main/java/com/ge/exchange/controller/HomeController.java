package com.ge.exchange.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HomeController {
    @GetMapping("/")
    public String displayLogin(Model model){
        model.addAttribute("text", "log in");
        return "login";
    }

    @GetMapping("/home")
    public String displayHome(Model model){
        return "home";
    }

    @GetMapping("/hello")
    public String displayHello(Model model){
        return "hello";
    }

}
