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
    private int requestId;

    private int requesterId;

    private int receiverId;

    private int exchangeId;

    private int requesterPostId;

    private int receiverPostId;
}
