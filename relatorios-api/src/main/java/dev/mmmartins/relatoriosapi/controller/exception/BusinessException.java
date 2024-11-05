package dev.mmmartins.relatoriosapi.controller.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException {
    private final HttpStatus status;
    private final String message;

    public BusinessException(final HttpStatus status, final String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public BusinessException(final String message) {
        this(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }
}
