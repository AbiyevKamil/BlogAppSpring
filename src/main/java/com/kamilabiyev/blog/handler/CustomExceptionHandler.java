package com.kamilabiyev.blog.handler;

import com.kamilabiyev.blog.exception.CustomException;
import com.kamilabiyev.blog.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e,
                                                               HttpServletRequest request) {
//        String method = request.getMethod();
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        String url = request.getRequestURL().toString();
//        var logString = String.format("Method: %s; \n\n User: %s; \n\n Requested url: %s", method, username, url);
//        log.info(logString);
        var response = new ErrorResponse(e.getMessage(), e.getStatus(), request.getRequestURI());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleCommonException(Exception e,
                                                                   HttpServletRequest request) {
//        String method = request.getMethod();
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        String url = request.getRequestURL().toString();
//        var logString = String.format("Method: %s; \n\n User: %s; \n\n Requested url: %s", method, username, url);
//        log.info(logString);
        var response = new ErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, request.getRequestURI());
        return new ResponseEntity<>(response, response.getStatus());
    }
}
