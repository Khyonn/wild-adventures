package com.wildadventures.msadventures.dao.entity;

import com.wildadventures.msadventures.dao.entity.pk.AdventureImagePK;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "adventure_images")
@Getter
@Setter
public class AdventureImage {
    @EmbeddedId
    private AdventureImagePK id;

    @MapsId("adventureId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adventure_id", referencedColumnName = "id")
    private Adventure adventure;

    @Column(name="is_main")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @NotNull
    private boolean isMain;

    @Column(name="width")
    @NotNull
    private int width;

    @Column(name = "height")
    @NotNull
    private int height;

    @Column(name = "url")
    @NotNull
    private String url;

    @Column(name = "label")
    @NotNull
    private String label;

}
