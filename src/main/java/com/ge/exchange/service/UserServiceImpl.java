package com.ge.exchange.service;

import com.ge.exchange.model.User;
import com.ge.exchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(User userRegistration) {
        User user = new User(
                userRegistration.getEmail(),
                passwordEncoder.encode(userRegistration.getPassword()),
                userRegistration.getFirstName(),
                userRegistration.getLastName(),
                userRegistration.getCity(),
                "ROLE_USER");
        return userRepository.save(user);
    }

    @Override
    public User update(User userInput) {
        User user = userRepository.getById(userInput.getId());
        user.setEmail(userInput.getEmail());
        user.setPassword(userInput.getPassword());
        user.setFirstName(userInput.getFirstName());
        user.setLastName(userInput.getLastName());
        user.setCity(userInput.getCity());
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password!");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Collections.singleton(mapRolesToAuthorities(user.getRole())));
    }

    private SimpleGrantedAuthority mapRolesToAuthorities(String role){
        return new SimpleGrantedAuthority(role);
    }

}