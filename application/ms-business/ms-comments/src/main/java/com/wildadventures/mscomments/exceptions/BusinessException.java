package com.wildadventures.mscomments.exceptions;

import lombok.Data;

@Data
public class BusinessException extends CommentAbstractException {
    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
