package com.karamanmert.auth_service.service.impl;

import com.karamanmert.auth_service.entity.AuthUser;
import com.karamanmert.auth_service.entity.Token;
import com.karamanmert.auth_service.enums.UserRole;
import com.karamanmert.auth_service.model.dto.AuthUserDetailsDto;
import com.karamanmert.auth_service.model.dto.AuthUserPrincipal;
import com.karamanmert.auth_service.model.request.CreateAuthUserRequest;
import com.karamanmert.auth_service.model.response.TokenResponse;
import com.karamanmert.auth_service.repository.AuthUserRepository;
import com.karamanmert.auth_service.service.spec.AuthService;
import com.karamanmert.auth_service.service.spec.JwtService;
import com.karamanmert.auth_service.service.spec.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author karamanmert
 * @date 11.11.2024
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenService tokenService;

    @Override
    public void createUser(CreateAuthUserRequest request) {
        this.checkUserNotExistWithUsername(request);

        final AuthUser authUser = buildAuthUserFromRequest(request);

        authUserRepository.save(authUser);

        log.info("Saved user: {}", authUser);
    }


    @Override
    public List<AuthUser> getAllUsers() {
        return authUserRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteUser(String username) {
        Optional<AuthUser> user = authUserRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new AuthenticationServiceException("User not found");
        }

        final Token token = user.get().getToken();
        if (Objects.nonNull(token)) {
            tokenService.delete(token);
        }

        authUserRepository.delete(user.get());
    }


    @Override
    @Transactional
    public TokenResponse login(Principal principal) {
        AuthUser user = this.extractEntityByPrincipleAndEntity(principal, AuthUser.class);

        if (user == null) {
            throw new AuthenticationServiceException("User not found");
        }

        AuthUserDetailsDto userDetailsDto = AuthUserDetailsDto.builder()
                .role(user.getRole())
                .email(user.getEmail())
                .name(user.getName())
                .build();

        TokenResponse tokenResponse = jwtService.generateToken(userDetailsDto);

        if (user.getToken() != null) {
            Token token = Token.builder()
                    .accessToken(tokenResponse.accessToken())
                    .user(user)
                    .build();
            user.setToken(token);
            tokenService.save(token);
        }

        this.authUserRepository.save(user);
        return tokenResponse;
        /*
        // we can also do these steps on loadByUsername part.
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                ));

        if (authentication.isAuthenticated()) {
            Optional<AuthUser> user = authUserRepository.findByUsername(request.getEmail());
            AuthUserDetailsDto userDetailsDto = null;
            if (user.isPresent()) {
                userDetailsDto = AuthUserDetailsDto.builder()
                        .role(user.get().getRole())
                        .email(user.get().getEmail())
                        .name(user.get().getName())
                        .build();
            }
            return jwtService.generateToken(userDetailsDto);
        } else {
            throw new AuthenticationServiceException("Invalid username or password");
        }

         */
    }

    @Override
    public void logout(String token) {

        if (token.isBlank()) {
            throw new RuntimeException("token must be not null");
        }

        tokenService.deleteByAccessToken(token);
    }

    @Override
    public AuthUser update(AuthUser user) {
        return null;
    }


    private AuthUser buildAuthUserFromRequest(CreateAuthUserRequest request) {
        final UserRole role = request.userRole() == null ? UserRole.ROLE_USER : request.userRole();
        final String encodedPassword = passwordEncoder.encode(request.password());

        return AuthUser.builder()
                .email(request.email())
                .password(encodedPassword)
                .name(request.name())
                .role(role)
                .build();
    }

    private <T> T extractEntityByPrincipleAndEntity(Principal principal, Class<T> clazz) {
        return Optional.of(principal)
                .map(UsernamePasswordAuthenticationToken.class::cast)
                .map(UsernamePasswordAuthenticationToken::getPrincipal)
                .filter(AuthUserPrincipal.class::isInstance)
                .map(AuthUserPrincipal.class::cast)
                .map(AuthUserPrincipal::authUser)
                .filter(clazz::isInstance)
                .map(clazz::cast)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private void checkUserNotExistWithUsername(CreateAuthUserRequest request) {
        Optional<AuthUser> user = authUserRepository.findByUsername(request.email());

        if (user.isPresent()) {
            throw new AuthenticationServiceException("User already exists");
        }
    }
}
