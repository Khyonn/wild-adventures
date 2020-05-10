package com.wildadventures.mscomments.exceptions;

import lombok.Data;

@Data
public class TechnicalException extends CommentAbstractException {
    public TechnicalException() {}

    public TechnicalException(String message) {
        super(message);
    }

    public TechnicalException(String message, Throwable cause) {
        super(message, cause);
    }
}
