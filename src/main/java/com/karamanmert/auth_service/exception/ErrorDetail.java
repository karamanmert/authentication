package com.karamanmert.auth_service.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author karamanmert
 */
@Builder
@Data
public class ErrorDetail {
    private String message;

    public ErrorDetail(String message) {
        this.message = message;
    }
}
