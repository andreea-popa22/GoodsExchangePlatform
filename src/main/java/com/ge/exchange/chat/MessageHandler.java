package com.ge.exchange.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class MessageHandler {

    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    public MessageHandler(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat")
    public void handleMessage(@DestinationVariable String sender, @DestinationVariable String receiver, @Payload String message) {
        messagingTemplate.convertAndSend("/topic/messages/" + sender + "/" + receiver, message);
    }
}

