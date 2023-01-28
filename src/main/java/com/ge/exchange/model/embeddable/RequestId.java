package com.ge.exchange.model.embeddable;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class RequestId implements Serializable {
    @Column(name = "post_id1", nullable = false)
    private int postId1;

    @Column(name = "post_id2", nullable = false)
    private int postId2;
}
