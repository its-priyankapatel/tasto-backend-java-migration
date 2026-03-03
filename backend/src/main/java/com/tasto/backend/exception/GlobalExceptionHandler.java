package com.tasto.backend.exception;

import com.tasto.backend.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponse>handleInvalidRequestException(InvalidRequestException e)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(false,e.getMessage()));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse>handleGlobalException(Exception e)
    {
        System.out.println(e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(false,"Internal Server Error"));
    }
}
