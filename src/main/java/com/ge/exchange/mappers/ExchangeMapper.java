package com.ge.exchange.mappers;

import com.ge.exchange.dto.ExchangeDto;
import com.ge.exchange.exception.ResourceNotFoundException;
import com.ge.exchange.model.Exchange;
import com.ge.exchange.model.Request;
import com.ge.exchange.model.Status;
import com.ge.exchange.repository.RequestRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ExchangeMapper {
    private RequestRepository requestRepository;

    public ExchangeMapper(RequestRepository requestRepository){
        this.requestRepository = requestRepository;
    }

    public Exchange fromExchangeDto(ExchangeDto exchangeDto) throws ResourceNotFoundException {
        Optional<Request> request = requestRepository.findById(exchangeDto.getRequestId());
        if (request.isEmpty()) {
            throw new ResourceNotFoundException("Exchange with id " + exchangeDto.getRequestId() + " does not exist.");
        }

        return new Exchange(exchangeDto.getExchangeId(),
                request.get(),
                exchangeDto.getDate(),
                exchangeDto.getLocation(),
                Status.valueOf(exchangeDto.getStatus()));
    }
}
