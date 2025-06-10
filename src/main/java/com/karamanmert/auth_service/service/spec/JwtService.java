package com.karamanmert.auth_service.service.spec;

import com.karamanmert.auth_service.entity.Token;
import com.karamanmert.auth_service.model.dto.AuthUserDetailsDto;
import com.karamanmert.auth_service.model.response.TokenResponse;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author karamanmert
 * @date 13.11.2024
 */
public interface JwtService {

    TokenResponse generateToken(AuthUserDetailsDto userDetails);

    String extractUsername(String token);

    boolean validateToken(String token, UserDetails userDetails);
}
