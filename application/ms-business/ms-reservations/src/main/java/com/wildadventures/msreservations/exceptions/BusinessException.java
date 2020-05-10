package com.wildadventures.msreservations.exceptions;

import lombok.Data;

@Data
public class BusinessException extends ReservationAbstractException {
    public BusinessException() {}

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
