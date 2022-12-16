package com.ge.exchange.service;

import com.ge.exchange.model.User;
import com.ge.exchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public String deleteUser(int id) {
        userRepository.deleteById(id);
        return "User removed!";
    }
    public User findUserById(int id) {
        User user = userRepository.findById(id).orElse(null);
        return user;
    }

    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        existingUser.setName(user.getName());
        existingUser.setSurname(user.getSurname());
        existingUser.setAddress(user.getAddress());
        existingUser.setCity(user.getCity());
        existingUser.setPhone(user.getPhone());
        return userRepository.save(existingUser);
    }
}