package org.homegrown.boardgamelibrary.exception;

public class NoCarModelIdFoundException extends RuntimeException {
    public NoCarModelIdFoundException(String message) {
        super(message);
    }

    public NoCarModelIdFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
