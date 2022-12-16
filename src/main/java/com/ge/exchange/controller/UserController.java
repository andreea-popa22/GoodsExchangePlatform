package com.ge.exchange.controller;

import com.ge.exchange.exception.ResourceNotFoundException;
import com.ge.exchange.model.User;
import com.ge.exchange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String add(@RequestBody User user){
        userService.saveUser(user);
        return "User addedd";
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