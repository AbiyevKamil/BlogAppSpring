package com.kamilabiyev.blog.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamilabiyev.blog.model.response.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthUnauthorizedEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) throws IOException, ServletException {
        log.error("Unauthorized exception messages: {}", ex.getMessage());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ErrorResponse apiErrorResponse = new ErrorResponse(ex.getMessage(),
                HttpStatus.UNAUTHORIZED, request.getRequestURI());

        OutputStream out = response.getOutputStream();
        objectMapper.writeValue(out, apiErrorResponse);
        out.flush();
    }
}
