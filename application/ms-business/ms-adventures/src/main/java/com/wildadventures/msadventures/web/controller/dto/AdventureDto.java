package com.wildadventures.msadventures.web.controller.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
public class AdventureDto {
    private int id;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private double price;
    private int maxParticipantNumber;
    private String categoryId;
    private List<AdventureImageDto> adventureImages;
}
