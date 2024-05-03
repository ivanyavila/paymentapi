package com.bancobase.paymentsapi.controller;

import com.bancobase.paymentsapi.entity.ApiError;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomErrorController {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleAllExceptions(ConstraintViolationException ex) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            sb.append(violation.getPropertyPath())
              .append(" : ")
              .append(violation.getMessage())
              .append(System.lineSeparator());
        }
        sb.append("}");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        logAndCreateApiError(HttpStatus.BAD_REQUEST, sb.toString())
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAllExceptions(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        logAndCreateApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage())
                );
    }

    private ApiError logAndCreateApiError(HttpStatus httpStatus, String message) {
        ApiError apiError = new ApiError(httpStatus);
        apiError.setMessage(message);
        log.error("{}",apiError);
        return apiError;
    }
}
