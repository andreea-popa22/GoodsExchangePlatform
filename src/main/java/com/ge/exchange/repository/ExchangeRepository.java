package com.ge.exchange.repository;

import com.ge.exchange.model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExchangeRepository extends JpaRepository<Exchange, Integer> {
    Optional<Exchange> findByExchangeId(int id);
}
