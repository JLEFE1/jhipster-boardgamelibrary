package org.homegrown.boardgamelibrary.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(NON_EMPTY)
public class ErrorResource extends ResourceSupport {

    private HttpStatus status;

    private String message;

    private String code;

    private String exceptionType;

    private Integer lineNumber;

    public ErrorResource(HttpStatus status, String message) {
        this(status, message, null);
    }

    public ErrorResource(HttpStatus status, String message, String code) {
        this.status = status;
        this.message = message;
        this.code = code;
        if (code == null && status != null) {
            this.code = status.toString();
        }
    }

    public ErrorResource(HttpStatus status, Throwable ex) {
        this(status, ex.getMessage());
        this.exceptionType = ex.getClass().getTypeName();
    }

    public ErrorResource(HttpStatus status, Throwable ex, String code) {
        this(status, ex.getMessage(), code);
        this.exceptionType = ex.getClass().getTypeName();
    }

    public ErrorResource(HttpStatus status, Throwable ex, String message, String code) {
        this(status, ex, code);
        if (message != null) {
            this.message = message;
        }
    }
}
