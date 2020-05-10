package com.wildadventures.msreservations.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InvoiceDto {
    private InvoiceIdDto id;
    private String participantNumber;
    private String adventureName;
    private LocalDateTime adventureDate;
    private double totalPrice;
    private LocalDateTime paymentDate;
}
