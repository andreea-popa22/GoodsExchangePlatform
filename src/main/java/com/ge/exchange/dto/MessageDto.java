package com.ge.exchange.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    @NotNull
    private int messageId;

    @NotNull
    private int senderId;

    @NotNull
    private int receiverId;

    @NotNull
    @NotBlank
    private String content;
}
