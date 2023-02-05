package com.ge.exchange.service;

import com.ge.exchange.model.Request;

import java.util.List;

public interface RequestService {
    public Request saveRequest(Request request);
    public List<Request> getAllRequests();
    public Request updateRequest(Request request);
    public String deleteRequest(int id);
    public Request findRequestById(int id);
    public List<Request> getReceivedRequests(int id);
}
