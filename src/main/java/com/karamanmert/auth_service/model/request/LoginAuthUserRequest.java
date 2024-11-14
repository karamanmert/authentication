package com.karamanmert.auth_service.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author karamanmert
 * @date 11.11.2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginAuthUserRequest {
    private String email;
    private String password;
}
