package org.homegrown.boardgamelibrary.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.google.common.collect.Lists;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class ExceptionAndErrorHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public HttpEntity<Resources<ErrorResource>> constraintsNotValid(ConstraintViolationException ex) {
        log.error("error occurred", ex);
        Resources<ErrorResource> res = new Resources<>(ex.getConstraintViolations().stream()
                .peek(constraintViolation -> log.error("constraint violation - message: {} property {} ", constraintViolation.getMessage(), constraintViolation.getPropertyPath().toString()))
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
    public HttpEntity<Resources<ErrorResource>> handleDataIntegrityException(DataIntegrityViolationException ex) {
        return handleException(HttpStatus.CONFLICT, ex);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public HttpEntity<Resources<ErrorResource>> handleAccessDeniedException(AccessDeniedException ex) {
        return handleException(HttpStatus.FORBIDDEN, ex);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public HttpEntity<Resources<ErrorResource>> handleInvalidFormat(InvalidFormatException ex) {
        return handleException(HttpStatus.BAD_REQUEST, ex, "Invalid format!", ex.getPath().get(0).getFieldName());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public HttpEntity<Resources<ErrorResource>> handleEntityNotFoundException(EntityNotFoundException ex) {
        return handleException(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(FileFormatException.class)
    public HttpEntity<Resources<ErrorResource>> handleFileFormatException(FileFormatException ex) {
        return handleException(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public HttpEntity<Resources<ErrorResource>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return handleException(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(FunctionalException.class)
    public HttpEntity<Resources<ErrorResource>> handleFunctionalException(FunctionalException ex) {
        return handleException(HttpStatus.BAD_REQUEST, ex, null, ex.getFieldName());
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public HttpEntity<Resources<ErrorResource>> handlePropertyPreferenceException(PropertyReferenceException ex) {
        return handleException(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(HystrixRuntimeException.class)
    public HttpEntity<Resources<ErrorResource>> hystrixFailure(HystrixRuntimeException e) {
        if (e.getCause() instanceof FeignException) {
            return handleException(HttpStatus.NOT_FOUND, e.getCause(), e.getCause().getMessage());
        }
        if (e.getCause() instanceof RuntimeException) {
            return handleException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause(), e.getCause().getMessage());
        }
        if (e.getCause() instanceof TimeoutException) {
            return handleException(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause(), e.getCause().getMessage());
        }
        return null;
    }

    private HttpEntity<Resources<ErrorResource>> handleException(HttpStatus status, Throwable ex) {
        return handleException(status, ex, null);
    }

    private HttpEntity<Resources<ErrorResource>> handleException(HttpStatus status, Throwable ex, String message) {
        return handleException(status, ex, message, null);
    }

    private HttpEntity<Resources<ErrorResource>> handleException(HttpStatus status, Throwable ex, String message, String code) {
        log.error("exception occured message: {}, code: {}, status {} ", message, code, status, ex);
        Resources<ErrorResource> res = new Resources<>(Lists.<ErrorResource>newArrayList(new ErrorResource(status, ex, message, code)));
        return new ResponseEntity<>(res, status);
    }
}
