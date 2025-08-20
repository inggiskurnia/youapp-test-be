package com.youapp.YouApp.common.exceptions;

import com.youapp.YouApp.common.response.ApiResponse;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(UserNotFoundExeption.class)
    public ResponseEntity<?> userNotFoundException(UserNotFoundExeption ex){
        return ApiResponse.failedResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<?> duplicateUserException(DuplicateUserException ex){
        return ApiResponse.failedResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
    }


}
