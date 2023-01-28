package com.ge.exchange.dto;

import com.ge.exchange.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeDto {
    @NotNull
    private int exchangeId;

    @NotNull
    private int requestId;

    @NotNull
    private Date date;

    @NotBlank
    @NotNull
    private String location;

    @NotBlank
    @NotNull
    private Status status;
}
