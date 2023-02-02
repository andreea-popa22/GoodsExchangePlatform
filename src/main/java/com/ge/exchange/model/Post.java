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
@Table(name = "POST")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int postId;

    @NotBlank
    @NotNull
    private String title;

    @NotBlank
    @NotNull
    @Enumerated(EnumType.STRING)
    private PostCategory category;

    @NotNull
    private Date date;

    @NotBlank
    @NotNull
    private String content;

    @Column(name = "photo_source")
    private String photoSource;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
}
