package com.karamanmert.auth_service.exception.handler;

import com.karamanmert.auth_service.exception.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;

/**
 * @author karamanmert
 */
@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = AuthenticationServiceException.class)
    public ResponseEntity<ErrorDetail> handleException(AuthenticationServiceException exception) {
        ErrorDetail detail = ErrorDetail.builder()
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(detail);
    }
}
