package com.tracker.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(RequestLoggingFilter.class);
    private static final int CACHE_LIMIT = 1024 * 1024;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        ContentCachingRequestWrapper wrappedRequest =
                new ContentCachingRequestWrapper(request, CACHE_LIMIT);

        filterChain.doFilter(wrappedRequest, response);

        String body = wrappedRequest.getContentAsString();
        if (body == null || body.isEmpty()) {
            body = "<empty>";
        }

        log.info("Request: method={}, uri={}, contentType={}, body={}",
                request.getMethod(),
                request.getRequestURI(),
                request.getContentType(),
                body);
    }
}
