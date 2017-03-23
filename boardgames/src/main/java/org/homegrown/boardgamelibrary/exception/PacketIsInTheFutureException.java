package org.homegrown.boardgamelibrary.exception;

public class PacketIsInTheFutureException extends RuntimeException {
    public PacketIsInTheFutureException(String message) {
        super(message);
    }

    public PacketIsInTheFutureException(String message, Throwable cause) {
        super(message, cause);
    }
}
