package dev.mmmartins.relatoriosapi.controller.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {
    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<Object> handleError(
            final BusinessException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getStatus(), exception.getMessage()), exception.getStatus());
    }
}
