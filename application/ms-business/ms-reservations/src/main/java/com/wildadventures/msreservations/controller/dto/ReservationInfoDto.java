package com.wildadventures.msreservations.controller.dto;

import lombok.Data;

@Data
public class ReservationInfoDto {
    private int actualReservationNumber;
    private UserReservationInfoDto currentUserReservationInfo;
}
