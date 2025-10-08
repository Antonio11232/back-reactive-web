package com.reactive.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> notFoundException(NotFoundException ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND.value(), ("NOT FOUND"), ex);
    }

    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<ErrorResponse> internalServerError(InternalServerError ex) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL SERVER ERROR", ex);
    }

    public ResponseEntity<ErrorResponse> buildErrorResponse(int status, String message, Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatusCode(status);
        errorResponse.setMessageError(message);
        errorResponse.setDescription(ex.getMessage());

        LOGGER.error("exception: {}", errorResponse);
        return ResponseEntity.status(status).body(errorResponse);
    }

}
