package com.karamanmert.auth_service.model.dto;

import com.karamanmert.auth_service.enums.UserRole;
import lombok.Builder;

/**
 * @author karamanmert
 * @date 14.11.2024
 */
@Builder
public record AuthUserDetailsDto(String email, String name, UserRole role) {
}
