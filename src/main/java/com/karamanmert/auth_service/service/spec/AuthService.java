package com.karamanmert.auth_service.service.spec;

import com.karamanmert.auth_service.entity.AuthUser;
import com.karamanmert.auth_service.model.request.CreateAuthUserRequest;
import com.karamanmert.auth_service.model.response.TokenResponse;

import java.security.Principal;
import java.util.List;

/**
 * @author karamanmert
 * @date 15.11.2024
 */
public interface AuthService {

    void createUser(CreateAuthUserRequest request);

    List<AuthUser> getAllUsers();

    void deleteUser(String username);

    TokenResponse login(Principal principal);

    void logout(String token);

    AuthUser update(AuthUser user);
}
