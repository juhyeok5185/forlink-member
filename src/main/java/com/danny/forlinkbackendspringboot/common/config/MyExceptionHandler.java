package com.danny.forlinkbackendspringboot.common.config;

import com.danny.forlinkbackendspringboot.common.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        //valid 검증 오류시 return 하는 Handler
        String errorMessage = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
        ApiResponse<Object> response = new ApiResponse<>(
                errorMessage,
                400,
                null
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MyException.class)
    public ResponseEntity<ApiResponse<Object>> handleMyException(MyException ex) {
        ApiResponse<Object> response = new ApiResponse<>(
                ex.getMessage(),
                404,
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
