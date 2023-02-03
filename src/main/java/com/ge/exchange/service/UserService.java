package com.ge.exchange.service;

import com.ge.exchange.dto.UserDto;
import com.ge.exchange.exception.ResourceNotFoundException;
import com.ge.exchange.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    User save(User user);
    User update(User userDto);
    UserDto findUserByEmail(String email) throws ResourceNotFoundException;
}