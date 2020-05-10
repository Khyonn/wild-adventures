package com.wildadventures.msadventures.dao.entity.pk;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@Getter
@Setter
public class AdventureImagePK implements Serializable {
    @Column(name = "id")
    private int id;

    @Column(name = "adventure_id")
    private int adventureId;
}
