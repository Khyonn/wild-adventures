package com.wildadventures.msreservations.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderIncomeDto {

 
    private String adventureName;
    private LocalDateTime adventureDate;
    private String stripeTransactionId;
    private double totalPrice;
}
