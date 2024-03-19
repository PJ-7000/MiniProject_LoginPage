package com.example.user_registration.exception;

import com.example.user_registration.model.CustomErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleException(Exception ex) {
        // Log the exception
        logger.error("Unhandled exception occurred", ex);

        // Create a custom error response with a meaningful message
        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", ex.getMessage());

        // Return a ResponseEntity with the custom error response and HTTP status 500 (Internal Server Error)
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CustomErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        // Log the exception
        logger.error("IllegalArgumentException occurred", ex);

        // Create a custom error response with the exception message
        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request", ex.getMessage());

        // Return a ResponseEntity with the custom error response and HTTP status 400 (Bad Request)
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyPresentException.class)
    public ResponseEntity<CustomErrorResponse> handleUserAlreadyPresentException(UserAlreadyPresentException ex) {
        // Log the exception
        logger.error("UserAlreadyPresentException occurred", ex);

        // Create a custom error response with the exception message
        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.CONFLICT.value(), "Conflict", ex.getMessage());

        // Return a ResponseEntity with the custom error response and HTTP status 409 (Conflict)
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        // Log the exception
        logger.error("UserNotFoundException occurred", ex);

        // Create a custom error response with the exception message
        CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.NOT_FOUND.value(), "Not Found", ex.getMessage());

        // Return a ResponseEntity with the custom error response and HTTP status 404 (Not Found)
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
