package com.wildadventures.msreservations.dao.entity.pk;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class ReservationPK implements Serializable {
    @Column(name = "user_id")
    private String userId;

    @Column(name = "adventure_id")
    private int adventureId;

}
