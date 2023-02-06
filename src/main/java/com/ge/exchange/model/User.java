package com.ge.exchange.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @NotBlank
    @NotNull
    private String email;

    @NotBlank
    @NotNull
    private String password;

    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @NotNull
    private String city;

    @NotBlank
    @NotNull
    private String role;

    @OneToMany(mappedBy = "requester")
    List<Request> requestsForRequester;

    @OneToMany(mappedBy = "receiver")
    List<Request> requestsForReceiver;

    @OneToMany(mappedBy = "author")
    List<Post> posts;

    @OneToMany(mappedBy = "user")
    List<Notification> notifications;
}