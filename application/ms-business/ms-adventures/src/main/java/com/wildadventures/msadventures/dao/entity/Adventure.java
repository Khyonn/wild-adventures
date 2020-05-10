package com.wildadventures.msadventures.dao.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "adventures")
@Getter
@Setter
public class Adventure {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int adventureId;

    @Column(name="name")
    @NotNull
    private String name;

    @Column(name="description")
    @NotNull
    private String description;

    @Column(name = "start_date")
    @NotNull
    private LocalDateTime dateTime;

    @Column(name = "price")
    @NotNull
    private double price;

    @Column(name = "max_participant_number")
    @NotNull
    private int maxParticipantNumber;

    @Column(name = "category")
    @NotNull
    private String category;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "adventure")
    private List<AdventureImage> images;
}
