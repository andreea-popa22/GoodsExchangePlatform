package com.ge.exchange.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {
    @GetMapping("/")
    public String displayLogin(Model model){
        model.addAttribute("text", "log in");
        return "loginV";
    }

    @GetMapping("/home")
    public String displayHome(Model model){
        return "homeV";
    }

    @GetMapping("/hello")
    public String displayHello(Model model){
        model.addAttribute("text", "test");
        return "helloV";
    }

}
