package com.karamanmert.auth_service.model.request;

import com.karamanmert.auth_service.enums.UserRole;
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
public class CreateAuthUserRequest {
    private String name;
    private String email;
    private String password;
    private UserRole role;
}
