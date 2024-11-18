package com.karamanmert.auth_service.model.request;

import com.karamanmert.auth_service.enums.UserRole;
import lombok.Builder;

/**
 * @author karamanmert
 * @date 11.11.2024
 */
@Builder
public record CreateAuthUserRequest(String name, String email, String password, UserRole userRole) {
}
