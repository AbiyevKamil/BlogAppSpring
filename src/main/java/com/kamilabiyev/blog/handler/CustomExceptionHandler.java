package com.kamilabiyev.blog.handler;

import com.kamilabiyev.blog.exception.CustomException;
import com.kamilabiyev.blog.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e,
                                                               HttpServletRequest request) {
        var response = new ErrorResponse(e.getMessage(), e.getStatus(), request.getRequestURI());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleCommonException(Exception e,
                                                                   HttpServletRequest request) {
        var response = new ErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, request.getRequestURI());
        return new ResponseEntity<>(response, response.getStatus());
    }
}
