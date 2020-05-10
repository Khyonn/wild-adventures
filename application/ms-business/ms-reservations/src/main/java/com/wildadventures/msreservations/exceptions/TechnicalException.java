package com.wildadventures.msreservations.exceptions;

import lombok.Data;

@Data
public class TechnicalException extends ReservationAbstractException {
    public TechnicalException() {}

    public TechnicalException(String message) {
        super(message);
    }

    public TechnicalException(String message, Throwable cause) {
        super(message, cause);
    }
}
