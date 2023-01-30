package com.ge.exchange.mappers;

import com.ge.exchange.dto.MessageDto;
import com.ge.exchange.exception.ResourceNotFoundException;
import com.ge.exchange.model.Message;
import com.ge.exchange.model.User;
import com.ge.exchange.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MessageMapper {
    private UserRepository userRepository;

    public MessageMapper(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Message fromMessageDto(MessageDto messageDto) throws ResourceNotFoundException {
        Optional<User> sender = userRepository.findByUserId(messageDto.getSenderId());
        if (sender.isEmpty()) {
            throw new ResourceNotFoundException("Sender with id " + messageDto.getSenderId() + " does not exist.");
        }

        Optional<User> receiver = userRepository.findByUserId(messageDto.getReceiverId());
        if (receiver.isEmpty()) {
            throw new ResourceNotFoundException("Receiver with id " + messageDto.getSenderId() + " does not exist.");
        }

        return new Message(messageDto.getMessageId(),
                sender.get(),
                receiver.get(),
                messageDto.getContent());
    }

    public MessageDto toMessageDto(Message message){
        return new MessageDto(message.getMessageId(),
                message.getSender().getUserId(),
                message.getReceiver().getUserId(),
                message.getContent());
    }
}
