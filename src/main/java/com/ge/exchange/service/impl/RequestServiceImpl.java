package com.ge.exchange.service.impl;

import com.ge.exchange.model.Request;
import com.ge.exchange.repository.RequestRepository;
import com.ge.exchange.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    private RequestRepository requestRepository;

    @Override
    public Request saveRequest(Request request) {
        return requestRepository.save(request);
    }

    @Override
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public String deleteRequest(int id) {
        requestRepository.deleteById(id);
        return "Request removed!";
    }
    public Request findRequestById(int id) {
        Request request = requestRepository.findById(id).orElse(null);
        return request;
    }

    public Request updateRequest(Request request) {
        Request existingRequest = requestRepository.findById(request.getRequestId()).orElse(null);
        existingRequest.setReceiver(request.getReceiver());
        existingRequest.setRequester(request.getRequester());
        return requestRepository.save(existingRequest);
    }
}
