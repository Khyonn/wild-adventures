package com.wildadventures.msreservations.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
public class ReservationLightDto {
    private ReservationIdDto id;
    @NotNull
    @Min(1)
    private int reservationsNb;
    private LocalDateTime reservationDate;
    private boolean isPayed;
}
