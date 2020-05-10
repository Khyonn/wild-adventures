package com.wildadventures.msreservations.controller.dto.mapper;

import com.wildadventures.msreservations.controller.dto.OrderIncomeDto;
import com.wildadventures.msreservations.dao.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderIncomeMapper {
   
    Order fromDtoToEntity(OrderIncomeDto orderIncomeDto);
}
