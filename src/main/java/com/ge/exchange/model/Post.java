package com.ge.exchange.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
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

    @OneToMany(mappedBy = "requesterPost")
    List<Request> requests1;

    @OneToMany(mappedBy = "receiverPost")
    List<Request> requests2;

    public Post(int postId, String title, PostCategory category, Date date, String content, String photoSource, User author) {
        this.postId = postId;
        this.title = title;
        this.category = category;
        this.date = date;
        this.content = content;
        this.photoSource = photoSource;
        this.author = author;
    }
}
