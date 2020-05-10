package com.wildadventures.msreservations.exceptions;

public class UnauthorizedException extends ReservationAbstractException {
    public UnauthorizedException() {}

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}
