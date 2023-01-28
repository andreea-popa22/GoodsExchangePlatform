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
public class PostDto {
    @NotNull
    private int postId;

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private String category;

    @NotNull
    @NotBlank
    private String date;

    @NotNull
    @NotBlank
    private String content;

    @NotNull
    private int authorId;
}
