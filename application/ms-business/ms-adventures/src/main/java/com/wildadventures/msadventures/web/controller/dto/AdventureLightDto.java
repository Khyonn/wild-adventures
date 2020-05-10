package com.wildadventures.msadventures.web.controller.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class AdventureLightDto {
    private int id;
    private String name;
    private LocalDateTime startDate;
    private double price;
    private AdventureImageDto image;
    private String categoryId;

}
