package com.ge.exchange.controller;

import com.ge.exchange.dto.UserDto;
import com.ge.exchange.exception.ResourceNotFoundException;
import com.ge.exchange.model.Notification;
import com.ge.exchange.model.User;
import com.ge.exchange.repository.NotificationRepository;
import com.ge.exchange.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/notification", headers = "Connection!=Upgrade")
@CrossOrigin
public class NotificationController {
    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String getAll(Principal principal, Model model) throws ResourceNotFoundException {
        UserDto user = userService.findUserByEmail(principal.getName());
        List<Notification> notifications = notificationRepository.getNotificationsForUser(user.getUserId());

        model.addAttribute("notifications", notifications);
        return "notifications";
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> deleteNotification(@PathVariable(value = "id") int id, Model model){
        Notification notification = notificationRepository.findById(id).get();
        notificationRepository.delete(notification);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/home").build().toUri();
        return ResponseEntity.status(HttpStatus.FOUND).location(location).build();
    }
}
