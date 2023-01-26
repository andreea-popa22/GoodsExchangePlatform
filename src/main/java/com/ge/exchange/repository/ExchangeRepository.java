package com.ge.exchange.repository;

import com.ge.exchange.model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepository extends JpaRepository<Exchange, Integer> {
}
