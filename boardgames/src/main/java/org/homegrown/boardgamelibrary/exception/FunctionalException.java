package org.homegrown.boardgamelibrary.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FunctionalException extends RuntimeException {
    private String fieldName;

    public FunctionalException() {
    }

    public FunctionalException(String message) {
        super(message);
    }

    public FunctionalException(String message, String fieldName) {
        super(message);
        this.fieldName = fieldName;
    }

    public FunctionalException(String message, Throwable cause) {
        super(message, cause);
    }

    public FunctionalException(Throwable cause) {
        super(cause);
    }
}
