package com.ge.exchange.model;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "REQUEST")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private int requestId;

    @ManyToOne
    @JoinColumn(name = "requester_id", referencedColumnName = "user_id")
    private User requester;

    @ManyToOne
    @JoinColumn(name = "receiver_id", referencedColumnName = "user_id")
    private User receiver;

    @ManyToOne
    @JoinColumn(name = "requester_post_id", referencedColumnName = "post_id")
    private Post requesterPost;

    @ManyToOne
    @JoinColumn(name = "receiver_post_id", referencedColumnName = "post_id")
    private Post receiverPost;

    @OneToOne(mappedBy = "request")
    private Exchange exchange;

    public Request(User requester, User receiver, Post requesterPost, Post receiverPost) {
        this.requester = requester;
        this.receiver = receiver;
        this.requesterPost = requesterPost;
        this.receiverPost = receiverPost;
    }
}
