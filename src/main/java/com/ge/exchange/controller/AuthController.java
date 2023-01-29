package com.ge.exchange.controller;

import com.ge.exchange.dto.UserDto;
import com.ge.exchange.model.User;

import javax.servlet.ServletException;
import javax.validation.Valid;

import com.ge.exchange.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("index")
//    public String home(){
//        return "index";
//    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    //    @PostMapping("/login")
//    public String login(String username, String password, HttpServletRequest request) {
//        try {
//            request.login(username, password);
//            return "redirect:/hello";
//        } catch (ServletException e) {
//            return "redirect:/login?error";
//        }
//    }
    // handler method to handle user registration request
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        User user = new User(); //TODO use DTO
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle register user form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") User user, //TODO use DTO implement mapper
                               BindingResult result,
                               Model model) {
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.save(user);
        return "redirect:/login";
    }

    @GetMapping("/hello")
    public String displayHello(Principal principal) {
        var a =principal.getName();

        //List<User> = userService.findByEmail(principal.)                      //TODO maybe use DTO
        //model.addAttribute("users", currentUser);
        return "helloV";
    }
}