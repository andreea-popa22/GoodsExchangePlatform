package com.ge.exchange.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {
    @NotNull
    private int requestId;

    @NotNull
    private int requesterId;

    @NotNull
    private int receiverId;

    @NotNull
    private int exchangeId;
}
