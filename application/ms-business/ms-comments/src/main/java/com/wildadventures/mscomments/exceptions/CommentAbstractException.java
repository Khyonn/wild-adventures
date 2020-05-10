package com.wildadventures.mscomments.exceptions;

import lombok.Data;

@Data
public abstract class CommentAbstractException extends Exception {
    public CommentAbstractException() {}

    public CommentAbstractException(String message) {
        super(message);
    }

    public CommentAbstractException(String message, Throwable cause) {
        super(message, cause);
    }
}
