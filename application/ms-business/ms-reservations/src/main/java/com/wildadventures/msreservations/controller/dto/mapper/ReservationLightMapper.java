package com.wildadventures.msreservations.controller.dto.mapper;

import com.wildadventures.msreservations.controller.dto.ReservationLightDto;
import com.wildadventures.msreservations.dao.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ReservationLightMapper {
    @Mappings({
            @Mapping(target = "id.userId", source = "id.userId"),
            @Mapping(target = "id.adventureId", source = "id.adventureId"),
    })
    ReservationLightDto fromEntityToDto(Reservation reservation);

    @Mappings({
            @Mapping(target = "id.userId", source = "id.userId"),
            @Mapping(target = "id.adventureId", source = "id.adventureId"),
    })
    Reservation fromDtoToEntity(ReservationLightDto reservationLightDto);
}

