package com.ge.exchange.controller;

import com.ge.exchange.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public Message sendMessage(@Payload Message chatMessage) {
        return chatMessage;
    }
}