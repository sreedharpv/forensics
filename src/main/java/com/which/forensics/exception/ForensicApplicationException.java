package com.which.forensics.exception;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class ForensicApplicationException extends RuntimeException {
    String errorMessage;

    public ForensicApplicationException(String message, String errorMessage) {
        super(message);
        this.errorMessage = errorMessage;
    }

    public ForensicApplicationException(String message, Throwable cause, String errorMessage) {
        super(message, cause);
        this.errorMessage = errorMessage;
    }

    public ForensicApplicationException(Throwable cause, String errorMessage) {
        super(cause);
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
