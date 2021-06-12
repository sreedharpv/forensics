package com.which.forensics.exception;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationException extends RuntimeException {
    String errorMessage;

    public AuthenticationException(String message) {
        super(message);
        this.errorMessage = message;
    }

    public AuthenticationException(String message, Throwable cause, String errorMessage) {
        super(message, cause);
        this.errorMessage = errorMessage;
    }

    public AuthenticationException(Throwable cause, String errorMessage) {
        super(cause);
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
