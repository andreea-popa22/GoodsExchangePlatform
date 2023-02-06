package com.ge.exchange.mappers;

import com.ge.exchange.dto.NotificationDto;
import com.ge.exchange.model.Notification;
import com.ge.exchange.model.User;
import com.ge.exchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NotificationMapper {
    @Autowired
    private UserRepository userRepository;

    public Notification fromNotificationDto(NotificationDto notificationDto){
        Optional<User> user = userRepository.findByUserId(notificationDto.getUserId());
        return new Notification(notificationDto.getNotificationId(),
                notificationDto.getContent(),
                user.get());
    }

    public NotificationDto toNotificationDto(Notification notification){
        return new NotificationDto(notification.getNotificationId(),
                notification.getContent(),
                notification.getUser().getUserId());
    }
}
