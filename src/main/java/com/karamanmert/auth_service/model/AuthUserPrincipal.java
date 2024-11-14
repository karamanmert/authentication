package com.karamanmert.auth_service.model;

import com.karamanmert.auth_service.entity.AuthUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * @author karamanmert
 * @date 13.11.2024
 */
// service?
public class AuthUserPrincipal implements UserDetails {

    private final transient AuthUser authUser;

    public AuthUserPrincipal(AuthUser authUser) {
        this.authUser = authUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(authUser.getRole().name()));
    }

    @Override
    public String getPassword() {
        return authUser.getPassword();
    }

    @Override
    public String getUsername() {
        return authUser.getEmail();
    }

    public AuthUser getAuthUser() {
        return authUser;
    }
}
