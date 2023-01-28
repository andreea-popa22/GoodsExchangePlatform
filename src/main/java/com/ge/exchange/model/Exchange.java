package com.ge.exchange.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EXCHANGE")
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int exchangeId;

    private int requestId;

    private Date date;

    private String location;

    private Status status;
}
