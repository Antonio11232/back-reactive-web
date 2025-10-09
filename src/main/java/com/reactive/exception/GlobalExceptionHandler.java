package com.reactive.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessResourceFailureException;
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

    @ExceptionHandler(DataAccessResourceFailureException.class)
    public ResponseEntity<ErrorResponse> handleMongoConnectionError(DataAccessResourceFailureException ex) {
        return buildErrorResponse(HttpStatus.SERVICE_UNAVAILABLE.value(),
                "Base de datos no disponible.", ex);
    }

    public ResponseEntity<ErrorResponse> buildErrorResponse(int status, String message, Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setStatusCode(status);
        errorResponse.setMessageError(message);
        errorResponse.setDescription(status == 503 ? "Timed out while waiting for a server." : ex.getMessage());

        LOGGER.error("exception: {}", errorResponse, ex);
        return ResponseEntity.status(status).body(errorResponse);
    }

}
