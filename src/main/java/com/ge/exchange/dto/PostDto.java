package com.ge.exchange.dto;

import com.ge.exchange.model.PostCategory;
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
public class PostDto {
    private int postId;

    private String title;

    private String category;

    private Date date;

    private String content;

    private String photoSource;

    private int authorId;
}
