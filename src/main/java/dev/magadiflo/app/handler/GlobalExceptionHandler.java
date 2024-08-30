package dev.magadiflo.app.handler;

import dev.magadiflo.app.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> userNotFoundException(UserNotFoundException exception, HttpHeaders headers,
                                                               HttpStatus status, WebRequest request) {

        log.info("Content-Type: {}", headers.getContentType());
        log.info("Status: {}", status.value());
        log.info("ContextPath: {}", request.getContextPath());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(Map.of("message", exception.getMessage())));
    }

}
