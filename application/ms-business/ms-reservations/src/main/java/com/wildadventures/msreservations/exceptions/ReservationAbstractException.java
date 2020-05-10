package com.wildadventures.msreservations.exceptions;

import lombok.Data;

@Data
public abstract class ReservationAbstractException extends Exception {
    public ReservationAbstractException() {}

    public ReservationAbstractException(String message) {
        super(message);
    }

    public ReservationAbstractException(String message, Throwable cause) {
        super(message, cause);
    }
}
