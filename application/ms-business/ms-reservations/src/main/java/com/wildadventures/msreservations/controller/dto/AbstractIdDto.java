package com.wildadventures.msreservations.controller.dto;

import lombok.Data;

@Data
public abstract class AbstractIdDto {
    private int adventureId;
    private String userId;
}
