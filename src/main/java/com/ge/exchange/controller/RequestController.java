package com.ge.exchange.controller;


import com.ge.exchange.exception.ResourceNotFoundException;
import com.ge.exchange.model.Request;
import com.ge.exchange.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/request")
@CrossOrigin
public class RequestController {
    @Autowired
    private RequestService requestService;

    @RequestMapping("/add")
    public String add(@RequestBody Request request){
        requestService.saveRequest(request);
        return "Request added";
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Request> get(@PathVariable(value = "id") int id) throws ResourceNotFoundException {
        try {
            Request request = requestService.findRequestById(id);
            return ResponseEntity.ok().body(request);
        }
        catch (Exception e) {
            throw new ResourceNotFoundException("Request not found with this id: " + id);
        }
    }

    @GetMapping("/getAll")
    public List<Request> list(){
        return requestService.getAllRequests();
    }

    @PutMapping("/update")
    public Request updateRequest(@RequestBody Request request) {
        return requestService.updateRequest(request);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        return requestService.deleteRequest(id);
    }
}
