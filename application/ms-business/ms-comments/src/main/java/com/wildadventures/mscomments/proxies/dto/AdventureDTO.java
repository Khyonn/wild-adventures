package com.wildadventures.mscomments.proxies.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdventureDTO {
    private int id;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private double price;
    private int maxParticipantNumber;
    private String categoryId;
}
