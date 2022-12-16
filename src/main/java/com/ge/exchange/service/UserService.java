package com.ge.exchange.service;

import com.ge.exchange.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User saveUser(User user);
    public List<User> getAllUsers();
    public User updateUser(User user);
    public String deleteUser(int id);
    public User findUserById(int id);

}