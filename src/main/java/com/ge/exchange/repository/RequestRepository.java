package com.ge.exchange.repository;

import com.ge.exchange.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface RequestRepository extends JpaRepository<Request,Integer> {
}
