package org.homegrown.boardgamelibrary.exception;

public class NoObdImageFoundException extends RuntimeException {
    public NoObdImageFoundException(String message) {
        super(message);
    }

    public NoObdImageFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
