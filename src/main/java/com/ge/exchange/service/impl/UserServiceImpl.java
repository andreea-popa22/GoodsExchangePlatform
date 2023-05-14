package com.ge.exchange.service.impl;

import com.ge.exchange.dto.UserDto;
import com.ge.exchange.exception.ResourceNotFoundException;
import com.ge.exchange.mappers.UserMapper;
import com.ge.exchange.model.User;
import com.ge.exchange.repository.UserRepository;
import com.ge.exchange.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(User userRegistration) {
//        User user = new User(
//                userRegistration.getEmail(),
//                passwordEncoder.encode(userRegistration.getPassword()),
//                userRegistration.getFirstName(),
//                userRegistration.getLastName(),
//                userRegistration.getCity(),
//                "ROLE_USER");
        userRegistration.setPassword(passwordEncoder.encode(userRegistration.getPassword()));
        userRegistration.setRole("ROLE_USER");
        return userRepository.save(userRegistration);
    }

    @Override
    public User update(User userInput) {
        User user = userRepository.getById(userInput.getUserId());
        user.setEmail(userInput.getEmail());
        user.setPassword(userInput.getPassword());
        user.setFirstName(userInput.getFirstName());
        user.setLastName(userInput.getLastName());
        user.setCity(userInput.getCity());
        return userRepository.save(user);
    }

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDto userDto = findUserByEmail(email);
        User user = userMapper.fromUserDto(userDto);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Collections.singleton(mapRolesToAuthorities(user.getRole())));
    }

    @Override
    public UserDto findUserByEmail(String email) throws ResourceNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isEmpty()) {
//            throw new ResourceNotFoundException("User with requested email does not exist.");
            return null;
        }
        return userMapper.toUserDto(user.get());
    }

    @Override
    public UserDto findUserById(Integer id) throws ResourceNotFoundException {
        Optional<User> user = userRepository.findByUserId(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User with requested email does not exist.");
        }
        return userMapper.toUserDto(user.get());
    }

    private SimpleGrantedAuthority mapRolesToAuthorities(String role){
        return new SimpleGrantedAuthority(role);
    }

}