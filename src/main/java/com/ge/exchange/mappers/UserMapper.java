package com.ge.exchange.mappers;

import com.ge.exchange.dto.NotificationDto;
import com.ge.exchange.dto.UserDto;
import com.ge.exchange.model.Notification;
import com.ge.exchange.model.Post;
import com.ge.exchange.model.Request;
import com.ge.exchange.model.User;
import com.ge.exchange.repository.NotificationRepository;
import com.ge.exchange.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    private UserRepository userRepository;
    private NotificationRepository notificationRepository;

    public UserMapper(UserRepository userRepository, NotificationRepository notificationRepository){
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
    }

    public User fromUserDto(UserDto userDto){
        List<Request> requestsForReceiver = userRepository.findRequestsForReceiver(userDto.getUserId());
        List<Request> requestsForRequester = userRepository.findRequestsForRequester(userDto.getUserId());
        List<Post> posts = userRepository.getPosts(userDto.getUserId());
        List<Notification> notifications = notificationRepository.getNotificationsForUser(userDto.getUserId());
        return new User(userDto.getUserId(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getCity(),
                userDto.getRole(),
                requestsForRequester,
                requestsForReceiver,
                posts,
                notifications);
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
