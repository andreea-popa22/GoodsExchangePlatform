package com.ge.exchange.controller;

import com.ge.exchange.dto.UserDto;
import com.ge.exchange.exception.ResourceNotFoundException;
import com.ge.exchange.mappers.UserMapper;
import com.ge.exchange.model.User;

import javax.validation.Valid;

import com.ge.exchange.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping(headers = "Connection!=Upgrade")
public class AuthController {

    private final UserService userService;
    private final UserMapper userMapper;

    public AuthController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }


    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

//    @PostMapping("/login")
//    public String login(String username, String password, HttpServletRequest request) {
//        try {
//             request.login(username, password);
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
    public String registration(@Valid @ModelAttribute("user") UserDto userDto, //TODO use DTO implement mapper
                               BindingResult result,
                               Model model) throws ResourceNotFoundException {
        UserDto existing = userService.findUserByEmail(userDto.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "register";
        }
        User user = userMapper.fromUserDto(userDto);
        userService.save(user);
        return "redirect:/login";
    }

}