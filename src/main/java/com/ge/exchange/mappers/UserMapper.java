package com.ge.exchange.mappers;

import com.ge.exchange.dto.UserDto;
import com.ge.exchange.model.Post;
import com.ge.exchange.model.Request;
import com.ge.exchange.model.User;
import com.ge.exchange.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    private UserRepository userRepository;

    public UserMapper(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User fromUserDto(UserDto userDto){
        List<Request> requestsForReceiver = userRepository.findRequestsForReceiver(userDto.getUserId());
        List<Request> requestsForRequester = userRepository.findRequestsForRequester(userDto.getUserId());
        List<Post> posts = userRepository.getPosts(userDto.getUserId());
        return new User(userDto.getUserId(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getCity(),
                userDto.getRole(),
                requestsForRequester,
                requestsForReceiver,
                posts);
    }

    public UserDto toUserDto(User user){
        return new UserDto(user.getUserId(),
                user.getEmail(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getCity(),
                user.getRole());
    }
}
