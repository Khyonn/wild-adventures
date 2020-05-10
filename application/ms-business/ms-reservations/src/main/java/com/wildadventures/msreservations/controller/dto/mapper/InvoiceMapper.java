package com.wildadventures.msreservations.controller.dto.mapper;

import com.wildadventures.msreservations.controller.dto.InvoiceDto;
import com.wildadventures.msreservations.dao.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    @Mappings({
            @Mapping(target = "id.userId", source = "id.userId"),
            @Mapping(target = "id.adventureId", source = "id.adventureId"),
    })
    InvoiceDto fromEntityToDto(Order order);
}
