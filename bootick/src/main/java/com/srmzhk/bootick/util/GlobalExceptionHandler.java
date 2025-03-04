package com.srmzhk.bootick.util;

import com.srmzhk.bootick.dto.ErrorDto;
import com.srmzhk.bootick.exception.ItemAlreadyExistException;
import com.srmzhk.bootick.exception.ItemNotFoundException;
import com.srmzhk.bootick.exception.TimeIsOverException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(ItemNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorDto("ITEM_NOT_FOUND", ex.getMessage()));
    }

    @ExceptionHandler(ItemAlreadyExistException.class)
    public ResponseEntity<ErrorDto> handleItemAlreadyExistException(ItemAlreadyExistException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDto("ITEM_ALREADY_EXIST", ex.getMessage()));
    }

    @ExceptionHandler(TimeIsOverException.class)
    public ResponseEntity<ErrorDto> handleTimeIsOverException(TimeIsOverException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorDto("TIME_IS_OVER", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDto("INTERNAL_ERROR", "Internal server error"));
    }
}
