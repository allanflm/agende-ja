package com.allanfelipe.agendaja.exception;

import java.time.LocalDateTime;
import java.util.Map;

public record ApiErrorResponse(
        LocalDateTime timestamp,
        int status,
        String message,
        Map<String, String> fieldErrors) {

    public ApiErrorResponse(int status, String message) {
        this(LocalDateTime.now(), status, message, null);
    }

    public ApiErrorResponse(int status, String message, Map<String, String> fieldErrors) {
        this(LocalDateTime.now(), status, message, fieldErrors);
    }
}
