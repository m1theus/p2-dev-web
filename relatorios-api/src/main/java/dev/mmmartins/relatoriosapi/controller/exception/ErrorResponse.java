package dev.mmmartins.relatoriosapi.controller.exception;

import org.springframework.http.HttpStatus;

public record ErrorResponse(HttpStatus status, String message) {
}
