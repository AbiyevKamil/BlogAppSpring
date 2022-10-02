package com.kamilabiyev.blog.model.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

@Data
public class ErrorResponse {
    private String message;
    private HttpStatus status;
    private Timestamp timestamp;
    private List<ValidationErrorResponse> errors;
    private String path;

    public ErrorResponse(String message, HttpStatus status, String path) {
        this.message = message;
        this.status = status;
        this.path = path;
        this.timestamp = new Timestamp(Calendar.getInstance().getTimeInMillis());
    }

    public ErrorResponse(String message, HttpStatus status, String path, List<ValidationErrorResponse> errors) {
        this.message = message;
        this.status = status;
        this.path = path;
        this.errors = errors;
        this.timestamp = new Timestamp(Calendar.getInstance().getTimeInMillis());
    }


}
