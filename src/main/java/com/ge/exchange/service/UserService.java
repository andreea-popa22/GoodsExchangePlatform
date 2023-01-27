package com.ge.exchange.service;

import com.ge.exchange.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User save(User user);
    public List<User> getAllUsers();
    public User updateUser(User user);
    public String deleteUser(int id);
    public User findUserById(int id);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}