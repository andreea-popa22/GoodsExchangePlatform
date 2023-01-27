package com.ge.exchange.controller;

import com.ge.exchange.exception.ResourceNotFoundException;
import com.ge.exchange.model.User;
import com.ge.exchange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUserAccount(@RequestBody @Valid User user) {
        User savedUser = userService.save(user);
        return ResponseEntity.ok().body(savedUser);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> get(@PathVariable(value = "id") int id) throws ResourceNotFoundException {
        try {
            User user = userService.findUserById(id);
            return ResponseEntity.ok().body(user);
        }
        catch (Exception e) {
            throw new ResourceNotFoundException("User not found with this id: " + id);
        }
    }

    @GetMapping("/getAll")
    public List<User> list(){
        return userService.getAllUsers();
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        return userService.deleteUser(id);
    }
}