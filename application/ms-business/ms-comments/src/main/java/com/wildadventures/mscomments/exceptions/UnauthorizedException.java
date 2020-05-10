package com.wildadventures.mscomments.exceptions;

public class UnauthorizedException extends CommentAbstractException {
    public UnauthorizedException() {}

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}
