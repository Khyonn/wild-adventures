package com.wildadventures.msreservations.dao.entity.pk;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class OrderPK implements Serializable {
    @Column(name = "user_id")
    private String userId;

    @Column(name = "adventure_id")
    private int adventureId;
}
