package com.karamanmert.auth_service.controller;

import com.karamanmert.auth_service.model.request.CreateAuthUserRequest;
import com.karamanmert.auth_service.model.response.TokenResponse;
import com.karamanmert.auth_service.service.spec.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author karamanmert
 * @date 11.11.2024
 */

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Void> createUser(@RequestBody CreateAuthUserRequest request) {
        authService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(Principal principal) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.login(principal));
    }


    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(@RequestParam String token) {
        this.authService.logout(token);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
