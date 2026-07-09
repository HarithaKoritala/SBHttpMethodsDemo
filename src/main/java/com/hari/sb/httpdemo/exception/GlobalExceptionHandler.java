package com.hari.sb.httpdemo.exception;

import com.hari.sb.httpdemo.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(ProductNotFoundException.class)
        public ErrorResponse handleProductNotFoundExc(ProductNotFoundException exception){
            return ErrorResponse.builder()
                    .errMsg(exception.getMessage())
                    .status("FAIL")
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build();
        }
}
