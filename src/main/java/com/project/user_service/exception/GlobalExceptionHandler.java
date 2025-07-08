package com.project.user_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.project.user_service.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFounException(UserNotFoundException ex, WebRequest request) {

        ApiResponse<String> response = new ApiResponse<>("error", ex.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<?> handleUserNotFounException(UserAlreadyExistException ex, WebRequest request) {

        ApiResponse<String> response = new ApiResponse<>("error", ex.getMessage(), request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
