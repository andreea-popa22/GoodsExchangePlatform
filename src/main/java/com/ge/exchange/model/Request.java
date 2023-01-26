package com.ge.exchange.model;

import com.ge.exchange.model.embeddable.RequestId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "REQUEST")
public class Request {
    @EmbeddedId
    RequestId requestId;

    private Date date;

}
