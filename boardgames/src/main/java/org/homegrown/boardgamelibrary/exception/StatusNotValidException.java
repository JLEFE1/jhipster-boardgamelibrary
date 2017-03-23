package org.homegrown.boardgamelibrary.exception;

public class StatusNotValidException extends RuntimeException {
    public StatusNotValidException(String message) {
        super(message);
    }

    public StatusNotValidException(String message, Throwable cause) {
        super(message, cause);
    }
}
