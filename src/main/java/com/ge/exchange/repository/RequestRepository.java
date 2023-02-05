package com.ge.exchange.repository;

import com.ge.exchange.model.Post;
import com.ge.exchange.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request,Integer> {
    @Query("select r from Request r where r.receiver.userId = :userId")
    List<Request> getReceivedRequests(int userId);
}

