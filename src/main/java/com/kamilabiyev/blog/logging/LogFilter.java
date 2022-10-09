//package com.kamilabiyev.blog.logging;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//
//@Slf4j
//@Component
//@Order
//public class LogFilter extends OncePerRequestFilter {
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        String method = request.getMethod();
//        String username = SecurityContextHolder.getContext().getAuthentication().getName();
//        String url = request.getRequestURL().toString();
//        var logString = String.format("\n\nMethod: %s; \n\nRequested url: %s\n\nUser: %s;", method, url, username);
//        log.info(logString);
//        filterChain.doFilter(request, response);
//    }
//
//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request)
//            throws ServletException {
//        String url = request.getRequestURL().toString();
//        return isResourceUrl(url);
//    }
//
//    private boolean isResourceUrl(String url) {
//        boolean isResourceUrl = false;
//        List<String> resourceRequests = Arrays.asList(
//                "/css/", "/js/", "/scss/", "/fonts/", "/emails/",
//                ".css", ".js", ".scss", ".eot", ".svg", ".ttf", ".woff", ".otf", ".ico", ".png");
//        for (String resourceRequest : resourceRequests) {
//            if (url.contains(resourceRequest)) {
//                isResourceUrl = true;
//            }
//        }
//        return isResourceUrl;
//    }
//}
