package com.kamilabiyev.blog.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamilabiyev.blog.model.response.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex) throws IOException, ServletException {
        log.error("Access denied exception messages: {}", ex.getMessage());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        ErrorResponse apiErrorResponse = new ErrorResponse(ex.getMessage(),
                HttpStatus.FORBIDDEN, request.getRequestURI());

        OutputStream out = response.getOutputStream();
        objectMapper.writeValue(out, apiErrorResponse);
        out.flush();
    }
}
