package com.karamanmert.auth_service.controller;

import com.karamanmert.auth_service.entity.AuthUser;
import com.karamanmert.auth_service.service.impl.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author karamanmert
 * @date 14.11.2024
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
// jwt bearer token test controller
public class UserController {

    private final AuthService authService;

    @GetMapping("/all")
    // anybody with right bearer token can access
    public ResponseEntity<List<AuthUser>> getAllUsers() {
        return ResponseEntity.ok(authService.getAllUsers());
    }

    @DeleteMapping("/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')") // only admin can delete
    public ResponseEntity<Void> deleteUser(@PathVariable("username") String username) {
        authService.deleteUser(username);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}