package com.wildadventures.msreservations.beans;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdventureBean {

    private int id;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private double price;
    private int maxParticipantNumber;
    private String categoryId;

}
