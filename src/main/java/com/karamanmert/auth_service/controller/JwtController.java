package com.karamanmert.auth_service.controller;

import com.karamanmert.auth_service.entity.AuthUser;
import com.karamanmert.auth_service.enums.UserRole;
import com.karamanmert.auth_service.model.dto.AuthUserDetailsDto;
import com.karamanmert.auth_service.model.dto.AuthUserPrincipal;
import com.karamanmert.auth_service.model.response.TokenResponse;
import com.karamanmert.auth_service.service.spec.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author karamanmert
 * @date 18.11.2024
 */
@RestController
@RequestMapping("/jwt")
@RequiredArgsConstructor
/**
 test controller for token
 */
public class JwtController {

    private final JwtService jwtService;

    @GetMapping("/token")
    public ResponseEntity<TokenResponse> getToken() {
        AuthUserDetailsDto userDetailsDto = AuthUserDetailsDto.builder()
                .name("mert")
                .email("merttest@yopmail.com")// username
                .role(UserRole.ROLE_USER)
                .build();

        return ResponseEntity.ok(jwtService.generateToken(userDetailsDto));
        // eyJhbGciOiJIUzM4NCJ9.eyJyb2xlIjoiUk9MRV9VU0VSIiwibmFtZSI6Im1lcnQiLCJlbWFpbCI6Im1lcnR0ZXN0QHlvcG1haWwuY29tIiwic3ViIjoibWVydHRlc3RAeW9wbWFpbC5jb20iLCJpYXQiOjE3MzE5MzgwNTcsImV4cCI6MTczMTkzOTg1N30.jELkkdnmbDDpSqeSWz94GYCxw9uknpC0prs7ZEH_ssfyjYZZLlubUuYKZFpweZCF
    }

    // (you can take token from request param or path variable or headers)
    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateToken() {
        //run the getToken and use the token
        String token = "eyJhbGciOiJIUzM4NCJ9.eyJyb2xlIjoiUk9MRV9VU0VSIiwibmFtZSI6Im1lcnQiLCJlbWFpbCI6Im1lcnR0ZXN0QHlvcG1haWwuY29tIiwic3ViIjoibWVydHRlc3RAeW9wbWFpbC5jb20iLCJpYXQiOjE3MzE5MzgwNTcsImV4cCI6MTczMTkzOTg1N30.jELkkdnmbDDpSqeSWz94GYCxw9uknpC0prs7ZEH_ssfyjYZZLlubUuYKZFpweZCF";

        AuthUserPrincipal principal = new AuthUserPrincipal(
                AuthUser.builder()
                        .name("mert")
                        .email("merttest@yopmail.com")
                        .role(UserRole.ROLE_USER)
                        .build());

        return ResponseEntity.ok(jwtService.validateToken(token, principal));
    }

    @GetMapping("/username")
    public ResponseEntity<String> getUsername() {
        String token = "eyJhbGciOiJIUzM4NCJ9.eyJyb2xlIjoiUk9MRV9VU0VSIiwibmFtZSI6Im1lcnQiLCJlbWFpbCI6Im1lcnR0ZXN0QHlvcG1haWwuY29tIiwic3ViIjoibWVydHRlc3RAeW9wbWFpbC5jb20iLCJpYXQiOjE3MzE5MzgwNTcsImV4cCI6MTczMTkzOTg1N30.jELkkdnmbDDpSqeSWz94GYCxw9uknpC0prs7ZEH_ssfyjYZZLlubUuYKZFpweZCF";
        return ResponseEntity.ok(jwtService.extractUsername(token));
    }
}
