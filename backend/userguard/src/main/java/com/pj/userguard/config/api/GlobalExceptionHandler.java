package com.pj.userguard.config.api;

import com.pj.userguard.util.api.ExceptionResponse;
import com.pj.userguard.util.time.TimeUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<ExceptionResponse> handleException(RuntimeException exception, HttpServletRequest webRequest) {

        var principal = webRequest.getUserPrincipal();

        var exceptionResponse = new ExceptionResponse(exception.getMessage(),
                TimeUtils.getCurrentTimestamp().toString(),
                webRequest.getRequestURI(),
                principal != null ? principal.getName() : null);

        LOGGER.error(exceptionResponse.toString());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
