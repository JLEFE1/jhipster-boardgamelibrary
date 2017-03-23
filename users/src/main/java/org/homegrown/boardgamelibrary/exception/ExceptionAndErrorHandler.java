package org.homegrown.boardgamelibrary.exception;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;


@ControllerAdvice
@Slf4j
public class ExceptionAndErrorHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public HttpEntity<Resources<ErrorResource>> constraintsNotValid(ConstraintViolationException ex) {
        log.error("constraint violation", ex);
        Resources<ErrorResource> res = new Resources<>(ex.getConstraintViolations().stream()
                .peek(constraintViolation -> log.error("constraint violation has occurred {} {}", constraintViolation.getMessage(), constraintViolation.getPropertyPath()))
                .map(violation -> new ErrorResource(HttpStatus.BAD_REQUEST, ex, violation.getMessage(), violation.getPropertyPath().toString()))
                .collect(Collectors.toList()));
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionSystemException.class)
    public HttpEntity<Resources<ErrorResource>> transactionException(TransactionSystemException ex) {
        if (ex.getOriginalException() != null && ex.getOriginalException().getCause() instanceof ConstraintViolationException) {
            return constraintsNotValid((ConstraintViolationException) ex.getOriginalException().getCause());
        }
        return handleException(HttpStatus.INTERNAL_SERVER_ERROR, ex, ex.getMessage(), null);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public HttpEntity<Resources<ErrorResource>> duplicateAccount(DataIntegrityViolationException ex) {
        return handleException(HttpStatus.CONFLICT, ex, "Email address already exists", "email");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public HttpEntity<Resources<ErrorResource>> handleAccessDeniedException(AccessDeniedException exception) {
        return handleException(HttpStatus.FORBIDDEN, exception, "Access denied");
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public HttpEntity<Resources<ErrorResource>> userAlreadyExistsException(UserAlreadyExistsException exception) {
        return handleException(HttpStatus.CONFLICT, exception, "User already exists");
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public HttpEntity<Resources<ErrorResource>> userNotFoundException(EntityNotFoundException exception) {
        return handleException(HttpStatus.NOT_FOUND, exception, exception.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public HttpEntity<Resources<ErrorResource>> handleInvalidJSONException(HttpMessageNotReadableException exception) {
        if (exception.getCause().getCause() instanceof ValidationException) {
            ValidationException ex = (ValidationException) exception.getCause().getCause();
            return handleException(HttpStatus.BAD_REQUEST, ex, ex.getLocalizedMessage(), ex.getFieldName());
        }
        return handleException(HttpStatus.BAD_REQUEST, exception, "Invalid JSON format!");
    }

    @ExceptionHandler(ValidationException.class)
    public HttpEntity<Resources<ErrorResource>> handleValidationException(ValidationException exception) {
        return handleException(HttpStatus.BAD_REQUEST, exception, exception.getMessage(), exception.getFieldName());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public HttpEntity<Resources<ErrorResource>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return handleException(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(FunctionalException.class)
    public HttpEntity<Resources<ErrorResource>> handleFunctionalException(FunctionalException ex) {
        return handleException(HttpStatus.BAD_REQUEST, ex, null, ex.getFieldName());
    }

    private HttpEntity<Resources<ErrorResource>> handleException(HttpStatus status, Throwable ex) {
        return handleException(status, ex, null);
    }

    private HttpEntity<Resources<ErrorResource>> handleException(HttpStatus status, Throwable ex, String message) {
        log.error("exception has occurred status: {} message: {} ", status, message, ex);
        return handleException(status, ex, message, null);
    }

    private HttpEntity<Resources<ErrorResource>> handleException(HttpStatus status, Throwable ex, String message, String code) {
        log.error("exception has occurred status: {} message: {} code: {} ", status, message, code, ex);
        Resources<ErrorResource> res = new Resources<>(Lists.<ErrorResource>newArrayList(new ErrorResource(status, ex, message, code)));
        return new ResponseEntity<>(res, status);
    }
}
