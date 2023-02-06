package com.ge.exchange.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @Column(name = "exchange_id")
    private int exchangeId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "request_id", referencedColumnName = "request_id")
    private Request request;

    private Date date;

    private String location;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    public Exchange(Request request, Date date, String location, Status status) {
        this.request = request;
        this.date = date;
        this.location = location;
        this.status = status;
    }
}
