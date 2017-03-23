package org.homegrown.boardgamelibrary.exception;


import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(value = NOT_FOUND, reason = "User service is unavailable!")
public class UserServiceUnavailableException extends RuntimeException {
}
