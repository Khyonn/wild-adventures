package com.wildadventures.msreservations.controller.dto.mapper;

import com.wildadventures.msreservations.controller.dto.UserReservationInfoDto;
import com.wildadventures.msreservations.dao.entity.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationUserInfoMapper {

    UserReservationInfoDto fromEntityToDto(Reservation reservation);
}
