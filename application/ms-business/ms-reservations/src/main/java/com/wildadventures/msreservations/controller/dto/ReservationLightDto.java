package com.wildadventures.msreservations.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationLightDto {
    private ReservationIdDto id;
    private int reservationsNb;
    private LocalDateTime reservationDate;
    private boolean isPayed;
}
