package com.ge.exchange.mappers;

import com.ge.exchange.dto.RequestDto;
import com.ge.exchange.dto.UserDto;
import com.ge.exchange.exception.ResourceNotFoundException;
import com.ge.exchange.model.Exchange;
import com.ge.exchange.model.Request;
import com.ge.exchange.model.User;
import com.ge.exchange.repository.ExchangeRepository;
import com.ge.exchange.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RequestMapper {
    private UserRepository userRepository;
    private ExchangeRepository exchangeRepository;
    private UserMapper userMapper;

    public RequestMapper(UserRepository userRepository, ExchangeRepository exchangeRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.exchangeRepository = exchangeRepository;
        this.userMapper = userMapper;
    }

    public Request fromRequestDto(RequestDto requestDto) throws ResourceNotFoundException {
        Optional<User> requester = userRepository.findByUserId(requestDto.getRequesterId());
        if (requester.isEmpty()) {
            throw new ResourceNotFoundException("Requester with id " + requestDto.getRequesterId() + " does not exist.");
        }

        Optional<User> receiver = userRepository.findByUserId(requestDto.getReceiverId());
        if (receiver.isEmpty()) {
            throw new ResourceNotFoundException("Receiver with id " + requestDto.getReceiverId() + " does not exist.");
        }

        Optional<Exchange> exchange = exchangeRepository.findByExchangeId(requestDto.getExchangeId());
        if (exchange.isEmpty()) {
            throw new ResourceNotFoundException("Exchange with id " + requestDto.getExchangeId() + " does not exist.");
        }

        return new Request(requestDto.getRequestId(),
                requester.get(),
                receiver.get(),
                exchange.get());
    }

    public RequestDto toRequestDto(Request request){
        return new RequestDto(request.getRequestId(),
                request.getRequester().getUserId(),
                request.getReceiver().getUserId(),
                request.getExchange().getExchangeId());
    }
}
